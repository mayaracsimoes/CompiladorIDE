package compiladorIDE;

public class Token {
	private int id;
	private String lexeme;
	private int position;

	public Token(int id, String lexeme, int position) {
		this.id = id;
		this.lexeme = lexeme;
		this.position = position;
	}

	public final int getId() {
		return id;
	}

	public final String getLexeme() {
		return lexeme;
	}

	public final int getPosition() {
		return position;
	}

	public String getTokenName() {

		switch (id) {
		case Constants.DOLLAR:
			return "EOF";
		case Constants.identificador:
			return "identificador";
		case Constants.constante_int:
			return "constante_int";
		case Constants.constante_float:
			return "constante_float";
		case Constants.constante_bin:
			return "constante_bin";
		case Constants.constante_hexa:
			return "constante_hexa";
		case Constants.constante_str:
			return "constante_str";
		case Constants.pr_bin:
			return "bin";
		case Constants.pr_bool:
			return "bool";
		case Constants.pr_elif:
			return "elif";
		case Constants.pr_else:
			return "else";
		case Constants.pr_endif:
			return "endif";
		case Constants.pr_false:
			return "false";
		case Constants.pr_float:
			return "float";
		case Constants.pr_hexa:
			return "hexa";
		case Constants.pr_if:
			return "if";
		case Constants.pr_int:
			return "int";
		case Constants.pr_input:
			return "input";
		case Constants.pr_main:
			return "main";
		case Constants.pr_output:
			return "output";
		case Constants.pr_str:
			return "str";
		case Constants.pr_toInt:
			return "toInt";
		case Constants.pr_toBin:
			return "toBin";
		case Constants.pr_toHexa:
			return "toHexa";
		case Constants.pr_true:
			return "true";
		case Constants.pr_repeat:
			return "repeat";
		case Constants.pr_until:
			return "until";
		case Constants.TOKEN_28:
			return ",";
		case Constants.TOKEN_29:
			return ".";
		case Constants.TOKEN_30:
			return ";";
		case Constants.TOKEN_31:
			return "=";
		case Constants.TOKEN_32:
			return ":";
		case Constants.TOKEN_33:
			return "(";
		case Constants.TOKEN_34:
			return ")";
		case Constants.TOKEN_35:
			return "&";
		case Constants.TOKEN_36:
			return "|";
		case Constants.TOKEN_37:
			return "!";
		case Constants.TOKEN_38:
			return "==";
		case Constants.TOKEN_39:
			return "!=";
		case Constants.TOKEN_40:
			return "<";
		case Constants.TOKEN_41:
			return ">";
		case Constants.TOKEN_42:
			return "+";
		case Constants.TOKEN_43:
			return "-";
		case Constants.TOKEN_44:
			return "*";
		case Constants.TOKEN_45:
			return "/";
		}
		return lexeme;
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

	public String toString() {
		return getTokenName() + " " + id + " ( " + lexeme + " ) @ " + position;
	};
}
