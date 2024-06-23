package compiladorIDE;

import java.util.*;

public class Semantico implements Constants {
	private List<String> codigoObjeto = new ArrayList<>();
	private List<String> listaId = new ArrayList<>();
	private String operadorRelacional = "";
	private Stack<String> pilhaTipos = new Stack<>();
	private Stack<String> pilhaRotulos = new Stack<>();
	private Map<String, Simbolo> tabelaSimbolos = new HashMap<>();
	private int contadorRotulos = 0;

	public void executeAction(int action, Token token) throws SemanticError {
		
		switch (action) {
		case 100:
			acao100();
			break;
		case 101:
			acao101();
			break;
		case 102:
			acao102(token);
			break;
		case 103:
			acao103();
			break;
		case 104:
			acao104(token);
			break;
		case 105:
			acao105(token);
			break;
		case 106:
			acao106(token);
			break;
		case 107:
			acao107(token);
			break;
		case 108:
			acao108();
			break;
		case 109:
			acao109();
			break;
		case 110:
			acao110();
			break;
		case 111:
			acao111();
			break;
		case 112:
			acao112();
			break;
		case 113:
			acao113();
			break;
		case 114:
			acao114();
			break;
		case 115:
			acao115(token);
			break;
		case 116:
			acao116(token);
			break;
		case 117:
			acao117();
			break;
		case 118:
			acao118();
			break;
		case 119:
			acao119();
			break;
		case 120:
			acao120(token);
			break;
		case 121:
			acao121();
			break;
		case 122:
			acao122();
			break;
		case 123:
			acao123();
			break;
		case 124:
			acao124();
			break;
		case 125:
			acao125();
			break;
		case 126:
			acao126(token);
			break;
		case 127:
			acao127(token);
			break;
		case 128:
			acao128(token);
			break;
		case 129:
			acao129(token);
			break;
		case 130:
			acao130();
			break;
		default:
			throw new SemanticError("Ação semântica desconhecida: " + action, token.getPosition());
		}
	}

	public List<String> getCodigoObjeto() {
		return codigoObjeto;
	}

	private void acao100() {
		codigoObjeto.add(".assembly extern mscorlib {}");
		codigoObjeto.add(".assembly _codigoObjeto {}");
		codigoObjeto.add(".module _codigoObjeto.exe");
		codigoObjeto.add(".class public _UNICA {");
		codigoObjeto.add(".method static public void _principal() {");
		codigoObjeto.add(".entrypoint");
	}

	private void acao101() throws SemanticError {
		codigoObjeto.add("ret");
		codigoObjeto.add("} }");
		for (String id : tabelaSimbolos.keySet()) {
			Simbolo simbolo = tabelaSimbolos.get(id);
			if (!simbolo.isUsado()) {
				throw new SemanticError(id + " não usado", simbolo.getLinha());
			}
		}
	}

	private void acao102(Token token) throws SemanticError {
		String tipo = token.getLexeme();
		String tipoLocal = tipo.equals("int") || tipo.equals("float") ? tipo + "64" : tipo; // Concatena "64" para int
																							// ou float

		for (String id : listaId) {
			if (tabelaSimbolos.containsKey(id)) {
				throw new SemanticError(id + " já declarado", token.getPosition());
			} else {
				tabelaSimbolos.put(id, new Simbolo(id, tipo, false, token.getPosition()));
				codigoObjeto.add(".locals (" + tipoLocal + " " + id + ")");
			}
		}
	}

	private void acao103() {
		listaId.clear();
	}

	private void acao104(Token token) {
		String valor = token.getLexeme();
		for (String id : listaId) {
			codigoObjeto.add("ldc.i8 " + valor);
			codigoObjeto.add("stloc " + id);
			Simbolo simbolo = tabelaSimbolos.get(id);
			simbolo.setUsado(true);
			tabelaSimbolos.put(id, simbolo);
		}
	}

	private void acao105(Token token) {
		listaId.add(token.getLexeme());
	}

	private void acao106(Token token) throws SemanticError {
		String tipoExpressao = pilhaTipos.pop();
		if (tipoExpressao.equals("int64")) {
			codigoObjeto.add("conv.i8");
		}
		for (int i = 0; i < listaId.size() - 1; i++) {
			codigoObjeto.add("dup");
		}
		for (String id : listaId) {
			if (!tabelaSimbolos.containsKey(id)) {
				throw new SemanticError(id + " não declarado", token.getPosition());
			} else {
				codigoObjeto.add("stloc " + id);
				Simbolo simbolo = tabelaSimbolos.get(id);
				simbolo.setUsado(true);
				tabelaSimbolos.put(id, simbolo);
			}
		}
		listaId.clear();
	}

	private void acao107(Token token) throws SemanticError {
		for (String id : listaId) {
			if (!tabelaSimbolos.containsKey(id)) {
				throw new SemanticError(id + " não declarado", token.getPosition());
			} else {
				String tipo = tabelaSimbolos.get(id).getTipo();
				if (tipo.equals("int") || tipo.equals("float")) {
					tipo += "64"; // Concatenar "64" para int ou float
				}

				// Adiciona a leitura da linha de entrada
				codigoObjeto.add("call string [mscorlib]System.Console::ReadLine()");

				// Adiciona a conversão apropriada
				String readMethod = tipo.equals("int64") ? "int64::Parse"
						: tipo.equals("float64") ? "Double::Parse" : tipo.equals("bool") ? "bool::Parse" : null;

				if (readMethod != null) {
					codigoObjeto.add("call " + tipo + " [mscorlib]System." + readMethod + "(string)");
				}

				codigoObjeto.add("stloc " + id);
				Simbolo simbolo = tabelaSimbolos.get(id);
				simbolo.setUsado(true);
				tabelaSimbolos.put(id, simbolo);
			}
		}
		listaId.clear();
	}

	private void acao108() {
		String tipo = pilhaTipos.pop();
		System.out.println(tipo);
		// Verificar se o tipo desempilhado é int64
		if (tipo.equals("int")) {
			codigoObjeto.add("conv.i8"); // Converter para int64
		}

		// Gerar código objeto para escrever na saída
		codigoObjeto.add("call void [mscorlib]System.Console::Write(" + tipo + ")");
	}

	private void acao109() {
		String rotulo1 = novoRotulo();
		pilhaRotulos.push(rotulo1);
		String rotulo2 = novoRotulo();
		pilhaRotulos.push(rotulo2);
		codigoObjeto.add("brfalse " + rotulo2);
	}

	private void acao110() {
		String rotulo = pilhaRotulos.pop();
		codigoObjeto.add(rotulo + ":");
	}

	private void acao111() {
		String rotulo2 = pilhaRotulos.pop();
		String rotulo1 = pilhaRotulos.pop();
		codigoObjeto.add("br " + rotulo1);
		pilhaRotulos.push(rotulo1);
		codigoObjeto.add(rotulo2 + ":");
	}

	private void acao112() {
		String rotulo = pilhaRotulos.pop();
		codigoObjeto.add(rotulo + ":");
	}

	private void acao113() {
		String novoRotulo = novoRotulo();
		codigoObjeto.add(novoRotulo + ":");
		pilhaRotulos.push(novoRotulo);
	}

	private void acao114() {
		String rotuloDesempilhado = pilhaRotulos.pop();
		codigoObjeto.add("brfalse " + rotuloDesempilhado);
	}

	private void acao115(Token token) throws SemanticError {
		String tipo2 = pilhaTipos.pop();
		String tipo1 = pilhaTipos.pop();

		// Lógica para determinar o tipo resultante conforme a TABELA DE TIPOS
		String tipoResultante = verificarTipoResultado(tipo1, tipo2, "&");

		if (tipoResultante == null) {
			throw new SemanticError("Tipos incompatíveis para a operação", token.getPosition());
		}

		// Empilhar o tipo resultante na pilha de tipos
		pilhaTipos.push(tipoResultante);
	}

	private void acao116(Token token) throws SemanticError {
		String tipo2 = pilhaTipos.pop();
		String tipo1 = pilhaTipos.pop();

		// Lógica para determinar o tipo resultante conforme a TABELA DE TIPOS
		String tipoResultante = verificarTipoResultado(tipo1, tipo2, "|");

		if (tipoResultante == null) {
			throw new SemanticError("Tipos incompatíveis para a operação", token.getPosition());
		}

		// Empilhar o tipo resultante na pilha de tipos
		pilhaTipos.push(tipoResultante);
	}

	private void acao117() {
		pilhaTipos.push("bool");
		codigoObjeto.add("ldc.i4.1");
	}

	private void acao118() {
		pilhaTipos.push("bool");
		codigoObjeto.add("ldc.i4.0");
	}

	private void acao119() throws SemanticError {
		String tipoExpressao = pilhaTipos.pop(); // Desempilha o tipo da expressão

		// Gera código objeto para negação lógica
		codigoObjeto.add("ldc.i4.0"); // Carrega o valor 0 na pilha
		codigoObjeto.add("ceq"); // Compara com zero (0) para inverter o valor booleano

		// Empilha o tipo resultante da negação, que é bool
		pilhaTipos.push("bool");
	}

	private void acao120(Token token) {
		operadorRelacional = token.getLexeme();
	}

	private void acao121() throws SemanticError {
		String tipo2 = pilhaTipos.pop();
		String tipo1 = pilhaTipos.pop();

		// Determina o tipo resultante da operação relacional
		String tipoResultante = verificarTipoResultado(tipo1, tipo2, "calcular");

		// Gera código objeto para a operação relacional em IL
		switch (operadorRelacional) {
		case "==":
			codigoObjeto.add("ceq");
			break;
		case "!=":
			codigoObjeto.add("ceq");
			codigoObjeto.add("ldc.i4.0");
			codigoObjeto.add("ceq");
			break;
		case "<":
			codigoObjeto.add("clt");
			break;
		case ">":
			codigoObjeto.add("cgt");
			break;

		}

		// Empilha o tipo resultante da operação
		pilhaTipos.push(tipoResultante);
	}

	private void acao122() throws SemanticError {
		String tipo2 = pilhaTipos.pop();
		String tipo1 = pilhaTipos.pop();

		// Verificar tipo resultante da operação
		String tipoResultante = verificarTipoResultado(tipo1, tipo2, "calcular");

		// Gerar código objeto em IL
		switch (tipoResultante) {
		case "int64":
			codigoObjeto.add("add");
			break;
		case "float64":
			codigoObjeto.add("add");
			break;
		}

		// Empilhar o tipo resultante na pilha de tipos
		pilhaTipos.push(tipoResultante);
	}

	private void acao123() throws SemanticError {
		String tipo2 = pilhaTipos.pop();
		String tipo1 = pilhaTipos.pop();

		// Verificar tipo resultante da operação
		String tipoResultante = verificarTipoResultado(tipo1, tipo2, "calcular");

		// Gerar código objeto em IL
		switch (tipoResultante) {
		case "int64":
			codigoObjeto.add("sub");
			break;
		case "float64":
			codigoObjeto.add("sub");
			break;
		}

		// Empilhar o tipo resultante na pilha de tipos
		pilhaTipos.push(tipoResultante);
	}

	private void acao124() throws SemanticError {
		String tipo2 = pilhaTipos.pop();
		String tipo1 = pilhaTipos.pop();
		System.out.println("tipos: " + tipo1 + tipo2);
		// Verificar tipo resultante da operação
		String tipoResultante = verificarTipoResultado(tipo1, tipo2, "calcular");

		// Gerar código objeto em IL
		switch (tipoResultante) {
		case "int64":
			codigoObjeto.add("mul");
			break;
		case "float64":
			codigoObjeto.add("mul");
			break;
		}

		// Empilhar o tipo resultante na pilha de tipos
		pilhaTipos.push(tipoResultante);
	}

	private void acao125() throws SemanticError {
		String tipo2 = pilhaTipos.pop();
		String tipo1 = pilhaTipos.pop();

		// Verificar tipo resultante da operação
		String tipoResultante = verificarTipoResultado(tipo1, tipo2, "calcular");

		// Gerar código objeto em IL
		switch (tipoResultante) {
		case "int64":
			codigoObjeto.add("div");
			break;
		case "float64":
			codigoObjeto.add("div");
			break;

		}

		// Empilhar o tipo resultante na pilha de tipos
		pilhaTipos.push(tipoResultante);
	}

	private void acao126(Token token) throws SemanticError {
		String lexeme = token.getLexeme();

		// Verificar se o identificador está na tabela de símbolos
		if (!tabelaSimbolos.containsKey(lexeme)) {
			throw new SemanticError(lexeme + " não declarado", token.getPosition());
		}

		// Obter o tipo do identificador da tabela de símbolos
		String tipo = tabelaSimbolos.get(lexeme).getTipo();

		// Gerar código objeto para carregar o valor do identificador
		codigoObjeto.add("ldloc " + lexeme);

		// Verificar se o tipo do identificador é int64 para conversão
		if (tipo.equals("int")) {
			codigoObjeto.add("conv.r8"); // Converter para float64 (conv.r8)
			tipo = "float64"; // Atualizar tipo para float64 na pilha de tipos
		}
		if (tipo.equals("float")) {
			tipo = "float64"; // Atualizar tipo para float64 na pilha de tipos
		}
		// Empilhar o tipo do identificador na pilhaTipos
		pilhaTipos.push(tipo);
	}

	private void acao127(Token token) {
		// Empilhar na pilha_tipos o tipo correspondente, conforme TABELA DE TIPOS, ou
		// seja, int64
		pilhaTipos.push("int64");

		// Gerar código objeto para carregar o valor da constante
		codigoObjeto.add("ldc.i8 " + token.getLexeme());

		// Como cte_int é tratada como float64 em IL, converter para float64 (conv.r8)
		codigoObjeto.add("conv.r8");
	}

	private void acao128(Token token) {
		pilhaTipos.push("float64");
		codigoObjeto.add("ldc.r8 " + token.getLexeme());
	}

	private void acao129(Token token) {
		pilhaTipos.push("string");
		codigoObjeto.add("ldstr " + token.getLexeme());
	}

	private void acao130() {
		codigoObjeto.add("ldc.i8 -1"); // Carregar -1 na pilha como um inteiro de 64 bits
		codigoObjeto.add("conv.r8"); // Converter para float64 (se necessário)
		codigoObjeto.add("mul"); // Multiplicar pelo operando anterior na expressão
	}

	private String novoRotulo() {
		return "rotulo" + (contadorRotulos++);
	}

	class Simbolo {
		private String id;
		private String tipo;
		private boolean usado;
		private int linha;

		public Simbolo(String id, String tipo, boolean usado, int linha) {
			this.id = id;
			this.tipo = tipo;
			this.usado = usado;
			this.linha = linha;
		}

		public String getId() {
			return id;
		}

		public String getTipo() {
			return tipo;
		}

		public boolean isUsado() {
			return usado;
		}

		public void setUsado(boolean usado) {
			this.usado = usado;
		}

		public int getLinha() {
			return linha;
		}
	}

	private String verificarTipoResultado(String operando1, String operando2, String operacao) {
		// Consultar a tabela de tipos para operações binárias
		if (operacao.equals("calcular")) {
			if (operando1.equals("int64") && operando2.equals("int64")) {
				return "int64";
			} else if ((operando1.equals("int64") && operando2.equals("float64"))
					|| (operando1.equals("float64") && operando2.equals("int64"))
					|| (operando1.equals("float64") && operando2.equals("float64"))) {
				return "float64";
			}
		} else if (operacao.equals("comparar")) {

			return "bool";

		} else if (operacao.equals("&") || operacao.equals("|")) {
			if (operando1.equals("bool") && operando2.equals("bool")) {
				return "bool";
			}
		}

		// Caso não seja possível determinar o tipo resultante, retorna null ou lança
		// uma exceção
		return null;
	}

}
