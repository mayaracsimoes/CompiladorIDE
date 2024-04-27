package compiladorIDE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CompilerIDE extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea editorTextArea;
	private JTextArea messagesTextArea;
	private JLabel statusLabel;
	private boolean arquivoSalvoAnteriormente = false;
	private File arquivoAtual;

	public class NumberedBorder extends AbstractBorder {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 *
		 */
		private int lineHeight;
		private final int characterHeight = 8;
		private final int characterWidth = 7;
		private final Color myColor;

		public NumberedBorder() {
			myColor = new Color(164, 164, 164);
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			JTextArea textArea = (JTextArea) c;
			Font font = textArea.getFont();
			FontMetrics metrics = g.getFontMetrics(font);
			lineHeight = metrics.getHeight();

			Color oldColor = g.getColor();
			g.setColor(myColor);

			double r = (double) height / (double) lineHeight;
			int rows = (int) (r + 0.5);
			String str = String.valueOf(rows);
			int lineLeft = calculateLeft(height) + 10;

			int px = 0;
			int py = 0;
			int lenght = 0;

			int visibleLines = textArea.getHeight() / lineHeight;
			for (int i = 0; i < visibleLines; i++) {

				str = String.valueOf(i + 1);
				lenght = str.length();

				py = lineHeight * i + 14;
				px = lineLeft - (characterWidth * lenght) - 2;

				g.drawString(str, px, py);
			}

			g.drawLine(lineLeft, 0, lineLeft, height);

			g.setColor(oldColor);
		}

		@Override
		public Insets getBorderInsets(Component c) {
			int left = calculateLeft(c.getHeight()) + 13;
			return new Insets(1, left, 1, 1);
		}

		private int calculateLeft(int height) {
			double r = (double) height / (double) lineHeight;
			int rows = (int) (r + 0.5);
			String str = String.valueOf(rows);
			int lenght = str.length();
			return characterHeight * lenght;
		}
	}

	// Função para salvar o conteúdo do editor em um arquivo
	private void salvarArquivo(File arquivo) {
		if (arquivo != null) { // Verifica se o arquivo não é nulo
			String nomeArquivo = arquivo.getName();
			if (!nomeArquivo.toLowerCase().endsWith(".txt")) {
				// Se o arquivo não termina com .txt, adiciona a extensão .txt
				nomeArquivo += ".txt";
				arquivo = new File(arquivo.getParent(), nomeArquivo);
			}
			try (FileWriter writer = new FileWriter(arquivo)) {
				writer.write(editorTextArea.getText());
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(CompilerIDE.this, "Erro ao salvar o arquivo!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
			// Atualiza a variável para indicar que o arquivo foi salvo
			arquivoSalvoAnteriormente = true;
			// Atualiza a variável que representa o arquivo atualmente aberto
			arquivoAtual = arquivo;
		} else {
			JOptionPane.showMessageDialog(CompilerIDE.this, "Nenhum arquivo selecionado para salvar!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void compilarPrograma() {
		Lexico lexico = new Lexico();
		lexico.setInput(editorTextArea.getText());
		try {
			Token t = null;
			String descricao = adicionarEspacosCondicionais("linha", 7) + adicionarEspacosCondicionais("classe", 20)
					+ "lexema\n";
			String tokens = "";
			while ((t = lexico.nextToken()) != null) {

				String linhas = adicionarEspacosCondicionais(String.valueOf(t.getLinhaToken(editorTextArea.getText())),
						7);
				String classes = adicionarEspacosCondicionais(t.getTokenName(), 20);
				String lexemas = t.getLexeme();

				tokens = tokens + linhas + classes + lexemas + "\n";
				System.out.println(tokens);
			}

			// Exibir o conteúdo formatado na área de texto
			messagesTextArea.setText(descricao + tokens + "\nPrograma compilado com sucesso!");

		} catch (LexicalError e1) {
			if (e1.getMessage().equals("Simbolo invalido"))
				messagesTextArea.setText("Erro na linha " + e1.getLinhaToken(editorTextArea.getText()) + " - "
						+ e1.getToken(editorTextArea.getText()) + " " + e1.getMessage());
			else
				messagesTextArea.setText(
						"Erro na linha " + e1.getLinhaToken(editorTextArea.getText()) + " - " + e1.getMessage());
			messagesTextArea.setPreferredSize(new Dimension(500, messagesTextArea.getPreferredSize().height));
		}
	}

	public static String adicionarEspacosCondicionais(String palavra, int tamanhoDesejado) {
		// Calcular o espaço restante
		int espacoRestante = tamanhoDesejado - palavra.length();

		// Número de espaços condicionais com base no espaço restante
		int espacosCondicionais = Math.max(0, espacoRestante);

		// Criar uma string com espaços condicionais
		String espacos = new String(new char[espacosCondicionais]).replace('\0', ' ');

		// Combinar palavra e espaços condicionais e retornar
		return palavra + espacos;
	}

	public CompilerIDE() {
		setMinimumSize(new Dimension(900, 600));
		setTitle("Compiler Interface");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Barra de ferramentas
		JPanel toolBarPanel = new JPanel(new GridLayout(0, 1));
		toolBarPanel.setPreferredSize(new Dimension(150, 570));
		toolBarPanel.setMaximumSize(new Dimension(150, 570));
		toolBarPanel.setMinimumSize(new Dimension(150, 570));
		getContentPane().add(toolBarPanel, BorderLayout.WEST);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setMinimumSize(new Dimension(10, 570));
		buttonPanel.setPreferredSize(new Dimension(150, 570));
		buttonPanel.setMaximumSize(new Dimension(100, 570));
		toolBarPanel.add(buttonPanel);

		JButton btnnovoctrl = new JButton("\r\nNovo [CTRL + N]");

		btnnovoctrl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "novo");
		btnnovoctrl.getActionMap().put("novo", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// Limpar o conteúdo do editor
				editorTextArea.setText("");

				// Limpar o conteúdo da área de mensagens
				messagesTextArea.setText("");

				// Limpar o texto da barra de status
				statusLabel.setText("");

			}
		});
		btnnovoctrl.setIcon(new ImageIcon(CompilerIDE.class.getResource("/img/file.png")));
		btnnovoctrl.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnnovoctrl.setHorizontalTextPosition(SwingConstants.CENTER);
		btnnovoctrl.setBounds(10, 10, 130, 48);
		btnnovoctrl.setAlignmentY(0.0f);
		btnnovoctrl.setMargin(new Insets(2, 14, 0, 14));
		btnnovoctrl.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnnovoctrl.setPreferredSize(new Dimension(130, 48));
		btnnovoctrl.setMnemonic(KeyEvent.VK_N);
		btnnovoctrl.setActionCommand("Novo");
		btnnovoctrl.addActionListener(toolBarListener);
		buttonPanel.setLayout(null);
		buttonPanel.add(btnnovoctrl);

		JButton button2 = new JButton("Abrir [CTRL + O]");
		button2.setHorizontalTextPosition(SwingConstants.CENTER);
		button2.setVerticalTextPosition(SwingConstants.BOTTOM);
		button2.setIcon(new ImageIcon(CompilerIDE.class.getResource("/img/pasta.png")));
		// Botão Abrir
		button2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), "abrir");
		button2.getActionMap().put("abrir", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Criar um seletor de arquivo
				JFileChooser fileChooser = new JFileChooser();

				// Configurar o filtro para exibir apenas arquivos .txt
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto (.txt)", "txt");
				fileChooser.setFileFilter(filter);

				// Mostrar a caixa de diálogo para selecionar o arquivo
				int result = fileChooser.showOpenDialog(CompilerIDE.this);

				// Verificar se o usuário selecionou um arquivo
				if (result == JFileChooser.APPROVE_OPTION) {
					// Obter o arquivo selecionado
					File selectedFile = fileChooser.getSelectedFile();

					// Verificar se o arquivo possui a extensão .txt
					if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
						JOptionPane.showMessageDialog(CompilerIDE.this,
								"Por favor, selecione um arquivo de texto (.txt).", "Formato Inválido",
								JOptionPane.ERROR_MESSAGE);
						return; // Sai da ação do botão e não executa o código abaixo
					}

					try {
						// Ler o conteúdo do arquivo e carregar no editor
						FileReader reader = new FileReader(selectedFile);
						BufferedReader bufferedReader = new BufferedReader(reader);
						StringBuilder stringBuilder = new StringBuilder();
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							stringBuilder.append(line).append("\n");
						}
						bufferedReader.close();
						editorTextArea.setText(stringBuilder.toString());

						// Limpar a área de mensagens
						messagesTextArea.setText("");

						// Atualizar a barra de status com o nome do arquivo aberto
						statusLabel.setText("Arquivo aberto: " + selectedFile.getAbsolutePath());
					} catch (IOException ex) {
						// Tratar exceções de leitura do arquivo
						ex.printStackTrace();
						JOptionPane.showMessageDialog(CompilerIDE.this, "Erro ao abrir o arquivo.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		button2.setBounds(10, 60, 130, 48);
		button2.setAlignmentY(0.0f);
		button2.setMargin(new Insets(0, 14, 2, 14));
		button2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button2.setPreferredSize(new Dimension(130, 48));
		button2.setMnemonic(KeyEvent.VK_O);
		button2.setActionCommand("Abrir");
		button2.addActionListener(toolBarListener);
		buttonPanel.add(button2);

		JButton button3 = new JButton("Salvar [CTRL + S]");
		button3.setIcon(new ImageIcon(CompilerIDE.class.getResource("/img/salve-.png")));
		button3.setVerticalTextPosition(SwingConstants.BOTTOM);
		button3.setHorizontalTextPosition(SwingConstants.CENTER);
		button3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "salvar");
		button3.getActionMap().put("salvar", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/// Verificar se o arquivo já foi salvo anteriormente ou é novo
				if (arquivoSalvoAnteriormente) {
					// Se o arquivo já foi salvo anteriormente, apenas salve as alterações
					salvarArquivo(arquivoAtual);
				} else {
					// Se for um novo arquivo, permita que o usuário escolha o local e o nome do
					// arquivo
					JFileChooser fileChoose = new JFileChooser();
					int resultado = fileChoose.showSaveDialog(CompilerIDE.this);

					// Verificar se o usuário selecionou um local para salvar o arquivo
					if (resultado == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChoose.getSelectedFile();
						// Salvar o conteúdo do editor no arquivo selecionado
						salvarArquivo(selectedFile);
						// Atualizar a barra de status com o nome do arquivo salvo
						statusLabel.setText("Arquivo salvo: " + selectedFile.getAbsolutePath());
						// Atualizar a variável para indicar que o arquivo foi salvo
						arquivoSalvoAnteriormente = true;
					}
				}
				// Limpar a área de mensagens após salvar
				messagesTextArea.setText("");
			}
		});
		button3.setBounds(10, 110, 130, 48);
		button3.setAlignmentY(0.0f);
		button3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button3.setPreferredSize(new Dimension(130, 48));
		button3.setMnemonic(KeyEvent.VK_S);
		button3.setActionCommand("Salvar");
		button3.addActionListener(toolBarListener);
		buttonPanel.add(button3);

		JButton button4 = new JButton("Copiar [CTRL + C]");
		button4.setIcon(new ImageIcon(CompilerIDE.class.getResource("/img/copia-de.png")));
		button4.setHorizontalTextPosition(SwingConstants.CENTER);
		button4.setVerticalTextPosition(SwingConstants.BOTTOM);
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editorTextArea.copy();
			}
		});
		button4.setBounds(10, 160, 130, 48);
		// Botão Copiar
		button4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "copiar");
		button4.getActionMap().put("copiar", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editorTextArea.copy();
			}
		});
		button4.setAlignmentY(0.0f);
		button4.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button4.setPreferredSize(new Dimension(130, 48));
		button4.setMnemonic(KeyEvent.VK_S);
		button4.setActionCommand("Copiar");
		buttonPanel.add(button4);

		JButton button5 = new JButton("Colar [CTRL + V]");
		button5.setVerticalTextPosition(SwingConstants.BOTTOM);
		button5.setIcon(new ImageIcon(CompilerIDE.class.getResource("/img/colar.png")));
		button5.setHorizontalTextPosition(SwingConstants.CENTER);
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editorTextArea.paste();
			}

		});
		button5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), "colar");
		button5.getActionMap().put("colar", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editorTextArea.paste();
			}
		});
		button5.setBounds(10, 210, 130, 48);
		button5.setAlignmentY(0.0f);
		button5.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button5.setPreferredSize(new Dimension(130, 48));
		button5.setMnemonic(KeyEvent.VK_S);
		button5.setActionCommand("Colar");
		buttonPanel.add(button5);

		JButton button6 = new JButton("Recortar [CTRL + X]");
		button6.setIcon(new ImageIcon(CompilerIDE.class.getResource("/img/tesouras.png")));
		button6.setHorizontalTextPosition(SwingConstants.CENTER);
		button6.setVerticalTextPosition(SwingConstants.BOTTOM);
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editorTextArea.cut();
			}
		});
		button6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "recortar");
		button6.getActionMap().put("recortar", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editorTextArea.cut();
			}
		});
		button6.setBounds(10, 260, 130, 48);
		button6.setAlignmentY(0.0f);
		button6.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button6.setPreferredSize(new Dimension(130, 48));
		button6.setMnemonic(KeyEvent.VK_S);
		button6.setActionCommand("Recortar");
		buttonPanel.add(button6);

		JButton button7 = new JButton("Compilar [F7]");
		button7.setVerticalTextPosition(SwingConstants.BOTTOM);
		button7.setIcon(new ImageIcon(CompilerIDE.class.getResource("/img/botao-play.png")));
		button7.setHorizontalTextPosition(SwingConstants.CENTER);
		button7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				compilarPrograma();
			}
		});
		button7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0),
				"compilar");
		button7.getActionMap().put("compilar", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				compilarPrograma();

			}
		});

		button7.setBounds(10, 310, 130, 48);
		button7.setAlignmentY(0.0f);
		button7.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button7.setPreferredSize(new Dimension(130, 48));
		button7.setMnemonic(KeyEvent.VK_S);
		button7.setActionCommand("Compilar");
		buttonPanel.add(button7);

		JButton button8 = new JButton("Equipe [F1]");
		button8.setVerticalTextPosition(SwingConstants.BOTTOM);
		button8.setIcon(new ImageIcon(CompilerIDE.class.getResource("/img/amigos (1).png")));
		button8.setHorizontalTextPosition(SwingConstants.CENTER);
		button8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				messagesTextArea.setText("Equipe: Brenda Eising e Mayara Cardoso");
			}
		});
		button8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "equipe");
		button8.getActionMap().put("equipe", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				messagesTextArea.setText("Equipe: Brenda Eising e Mayara Cardoso");
			}
		});
		button8.setBounds(10, 360, 130, 48);
		button8.setAlignmentY(0.0f);
		button8.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button8.setPreferredSize(new Dimension(130, 48));
		button8.setMnemonic(KeyEvent.VK_S);
		button8.setActionCommand("Equipe");
		buttonPanel.add(button8);

		// Editor
		editorTextArea = new JTextArea();
		editorTextArea.setBorder(new NumberedBorder());
		JScrollPane editorScrollPane = new JScrollPane(editorTextArea);
		editorScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(600, 400));

		// Area para mensagens
		messagesTextArea = new JTextArea();
		messagesTextArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		messagesTextArea.setPreferredSize(new Dimension(700, 1000));
		messagesTextArea.setMaximumSize(new Dimension(900, 1000));
		messagesTextArea.setMinimumSize(new Dimension(900, 25));
		messagesTextArea.setEditable(false);
		JScrollPane messagesScrollPane = new JScrollPane(messagesTextArea);
		messagesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		messagesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		messagesScrollPane.setMaximumSize(new Dimension(900, 100));
		messagesScrollPane.setPreferredSize(new Dimension(500, 100));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editorScrollPane, messagesScrollPane);
		splitPane.setResizeWeight(0.7);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		// Barra de status
		statusLabel = new JLabel(" ");
		statusLabel.setMaximumSize(new Dimension(900, 25));
		statusLabel.setMinimumSize(new Dimension(900, 25));
		statusLabel.setPreferredSize(new Dimension(900, 25));
		getContentPane().add(statusLabel, BorderLayout.SOUTH);
	}

	private ActionListener toolBarListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch (command) {

			case "Novo":
				// Limpar o conteúdo do editor
				editorTextArea.setText("");

				// Limpar o conteúdo da área de mensagens
				messagesTextArea.setText("");

				// Limpar o texto da barra de status
				statusLabel.setText("");

				break;

			case "Abrir":
				// Criar um seletor de arquivo
				JFileChooser fileChooser = new JFileChooser();

				// Configurar o filtro para exibir apenas arquivos .txt
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto (.txt)", "txt");
				fileChooser.setFileFilter(filter);

				// Mostrar a caixa de diálogo para selecionar o arquivo
				int result = fileChooser.showOpenDialog(CompilerIDE.this);

				// Verificar se o usuário selecionou um arquivo
				if (result == JFileChooser.APPROVE_OPTION) {
					// Obter o arquivo selecionado
					File selectedFile = fileChooser.getSelectedFile();

					// Verificar se o arquivo possui a extensão .txt
					if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
						JOptionPane.showMessageDialog(CompilerIDE.this,
								"Por favor, selecione um arquivo de texto (.txt).", "Formato Inválido",
								JOptionPane.ERROR_MESSAGE);
						return; // Sai da ação do botão e não executa o código abaixo
					}

					try {
						// Ler o conteúdo do arquivo e carregar no editor
						FileReader reader = new FileReader(selectedFile);
						BufferedReader bufferedReader = new BufferedReader(reader);
						StringBuilder stringBuilder = new StringBuilder();
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							stringBuilder.append(line).append("\n");
						}
						bufferedReader.close();
						editorTextArea.setText(stringBuilder.toString());

						// Limpar a área de mensagens
						messagesTextArea.setText("");

						// Atualizar a barra de status com o nome do arquivo aberto
						statusLabel.setText("Arquivo aberto: " + selectedFile.getAbsolutePath());
					} catch (IOException ex) {
						// Tratar exceções de leitura do arquivo
						ex.printStackTrace();
						JOptionPane.showMessageDialog(CompilerIDE.this, "Erro ao abrir o arquivo.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				break;

			case "Salvar":
				// Verificar se o arquivo já foi salvo anteriormente ou é novo
				if (arquivoSalvoAnteriormente) {
					// Se o arquivo já foi salvo anteriormente, apenas salve as alterações
					salvarArquivo(arquivoAtual);
				} else {
					// Se for um novo arquivo, permita que o usuário escolha o local e o nome do
					// arquivo
					JFileChooser fileChoose = new JFileChooser();
					int resultado = fileChoose.showSaveDialog(CompilerIDE.this);

					// Verificar se o usuário selecionou um local para salvar o arquivo
					if (resultado == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChoose.getSelectedFile();
						// Salvar o conteúdo do editor no arquivo selecionado
						salvarArquivo(selectedFile);
						// Atualizar a barra de status com o nome do arquivo salvo
						statusLabel.setText("Arquivo salvo: " + selectedFile.getAbsolutePath());
						// Atualizar a variável para indicar que o arquivo foi salvo
						arquivoSalvoAnteriormente = true;
					}
				}
				// Limpar a área de mensagens após salvar
				messagesTextArea.setText("");
				break;

			case "Copiar":
				editorTextArea.copy();
				break;
			case "Colar":
				editorTextArea.paste();
				break;
			case "Recortar":
				editorTextArea.cut();
				break;
			case "Compilar":
				// Define o texto na área de mensagens
				// messagesTextArea.setText("Compilação de programas ainda não foi
				// implementada.");
				break;

			case "Equipe":
				messagesTextArea.setText("Equipe: Brenda Eising e Mayara Cardoso");
				break;
			default:
				break;
			}
		}
	};

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			CompilerIDE compilerInterface = new CompilerIDE();
			compilerInterface.setVisible(true);
		});
	}
}
