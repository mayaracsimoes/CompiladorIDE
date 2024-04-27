package compiladorIDE;

public class AnalysisError extends Exception {
	private int position;

	public AnalysisError(String msg, int position) {
		super(msg);
		this.position = position;
	}

	public AnalysisError(String msg) {
		super(msg);
		this.position = -1;
	}

	public int getPosition() {
		return position;
	}

	public int getLinhaToken(String text) {
		int line = 1;
		for (int i = 0; i < position && i < text.length(); i++) {
			if (text.charAt(i) == '\n') {
				line++;
			}
		}
		return line;
	}

	public String getToken(String text) {
		// Verifica se a posição está dentro dos limites do texto
		if (position >= 0 && position < text.length()) {
			// Retorna o token na posição específica
			return String.valueOf(text.charAt(position));
		} else {
			return ""; // Retorna uma string vazia se a posição estiver fora dos limites
		}
	}

}
