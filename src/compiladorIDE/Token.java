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
			return "palavra reservada";
		case Constants.pr_bool:
			return "palavra reservada";
		case Constants.pr_elif:
			return "palavra reservada";
		case Constants.pr_else:
			return "palavra reservada";
		case Constants.pr_endif:
			return "palavra reservada";
		case Constants.pr_false:
			return "palavra reservada";
		case Constants.pr_float:
			return "palavra reservada";
		case Constants.pr_hexa:
			return "palavra reservada";
		case Constants.pr_if:
			return "palavra reservada";
		case Constants.pr_int:
			return "palavra reservada";
		case Constants.pr_input:
			return "palavra reservada";
		case Constants.pr_main:
			return "palavra reservada";
		case Constants.pr_output:
			return "palavra reservada";
		case Constants.pr_str:
			return "palavra reservada";
		case Constants.pr_toInt:
			return "palavra reservada";
		case Constants.pr_toBin:
			return "palavra reservada";
		case Constants.pr_toHexa:
			return "palavra reservada";
		case Constants.pr_true:
			return "palavra reservada";
		case Constants.pr_repeat:
			return "palavra reservada";
		case Constants.pr_until:
			return "palavra reservada";
		case Constants.TOKEN_28:
			return "simbolo especial";
		case Constants.TOKEN_29:
			return "simbolo especial";
		case Constants.TOKEN_30:
			return "simbolo especial";
		case Constants.TOKEN_31:
			return "simbolo especial";
		case Constants.TOKEN_32:
			return "simbolo especial";
		case Constants.TOKEN_33:
			return "simbolo especial";
		case Constants.TOKEN_34:
			return "simbolo especial";
		case Constants.TOKEN_35:
			return "simbolo especial";
		case Constants.TOKEN_36:
			return "simbolo especial";
		case Constants.TOKEN_37:
			return "simbolo especial";
		case Constants.TOKEN_38:
			return "simbolo especial";
		case Constants.TOKEN_39:
			return "simbolo especial";
		case Constants.TOKEN_40:
			return "simbolo especial";
		case Constants.TOKEN_41:
			return "simbolo especial";
		case Constants.TOKEN_42:
			return "simbolo especial";
		case Constants.TOKEN_43:
			return "simbolo especial";
		case Constants.TOKEN_44:
			return "simbolo especial";
		case Constants.TOKEN_45:
			return "simbolo especial";
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
