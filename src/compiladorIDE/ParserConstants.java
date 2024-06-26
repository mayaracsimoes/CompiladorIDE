package compiladorIDE;

public interface ParserConstants
{
    int START_SYMBOL = 46;

    int FIRST_NON_TERMINAL    = 46;
    int FIRST_SEMANTIC_ACTION = 80;

    int[][] PARSER_TABLE =
    {
        { -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 22, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, -1, 22, -1, 22, -1, -1, -1, -1, -1, 22, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, -1, 26, -1, 27, -1, -1, -1, -1, -1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 15, 16, 17, 18, 19, -1, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1,  9, 13, -1, -1, -1, -1, 10, 12, -1, 11, -1, -1, -1, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 42, 42, 42, 42, 42, 42, -1, -1, -1, -1, -1, 42, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 42, -1, -1, -1, -1, -1, -1, -1, 42, -1, -1, -1, 42, -1, -1, -1, -1, 42, 42, -1, -1 },
        { -1, 33, 33, 33, 33, 33, 33, -1, -1, -1, -1, -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, 33, -1, -1, -1, -1, 33, 33, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, 37, 38, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 46, 46, 46, 46, 46, 46, -1, -1, -1, -1, -1, 48, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 47, -1, -1, -1, -1, -1, -1, -1, 46, -1, -1, -1, 49, -1, -1, -1, -1, 46, 46, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, 43, -1, 43, -1, 43, 44, 45, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 50, 50, 50, 50, 50, 50, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, -1, -1, -1, -1, -1, -1, -1, -1, 50, 50, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 51, -1, 51, -1, 51, -1, 51, 51, 51, -1, 52, 52, 52, 52, -1, -1, -1, -1 },
        { -1, 57, 57, 57, 57, 57, 57, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 57, -1, -1, -1, -1, -1, -1, -1, -1, 57, 57, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 58, -1, 58, -1, 58, -1, 58, 58, 58, -1, 58, 58, 58, 58, 59, 60, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 53, 54, 55, 56, -1, -1, -1, -1 },
        { -1, 61, 61, 61, 61, 61, 61, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 61, -1, -1, -1, -1, -1, -1, -1, -1, 61, 61, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 62, -1, 62, 62, 62, -1, 62, 62, 62, 62, 62, 62, 63, 64 },
        { -1, 65, 65, 65, 65, 65, 65, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 65, -1, -1, -1, -1, -1, -1, -1, -1, 65, 65, -1, -1 },
        { -1, 66, 67, 68, 69, 70, 71, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 72, -1, -1, -1, -1, -1, -1, -1, -1, 73, 74, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 75, 76, 75, -1, 75, -1, 75, 75, 75, -1, 75, 75, 75, 75, 75, 75, 75, 75 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 77, 78, 79, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  4,  5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  7, -1, -1,  8,  8, -1,  8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { 24, 23, -1, -1, -1, -1, -1, -1, -1, 24, 24, 24, -1, -1, -1, 23, -1, 23, -1, 23, -1, -1, -1, -1, -1, 23, 24, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 34, -1, -1, -1, -1, -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };

    int[][] PRODUCTIONS = 
    {
        { 180,  47,  19,  48, 181 },
        {  50,  47 },
        {   0 },
        {  51,  32,  53, 182,  76 },
        {  30, 183 },
        {  31,  52, 184,  30, 183 },
        {   2, 185,  77 },
        {  28,  51 },
        {   0 },
        {   8 },
        {  14 },
        {  17 },
        {  15 },
        {   9 },
        {  21 },
        {   3 },
        {   4 },
        {   5 },
        {   6 },
        {   7 },
        {  13 },
        {  25 },
        {  49,  30,  78 },
        {  48 },
        {   0 },
        {  54 },
        {  55 },
        {  56 },
        {  57 },
        {  58 },
        {  51,  31,  59, 186 },
        {  18,  33,  51, 187,  34 },
        {  20,  33,  60,  34 },
        {  59, 188,  79 },
        {  28,  60 },
        {   0 },
        {  16,  59, 189,  32,  48,  61, 191,  62,  12, 190 },
        { 191,  10,  59, 192,  32,  48,  61 },
        {   0 },
        {  11,  48 },
        {   0 },
        {  26, 193,  48,  27,  59, 194 },
        {  63,  64 },
        {   0 },
        {  35,  63, 195,  64 },
        {  36,  63, 196,  64 },
        {  65 },
        {  25, 197 },
        {  13, 198 },
        {  37,  63, 199 },
        {  67,  66 },
        {   0 },
        {  69, 200,  67, 201 },
        {  38 },
        {  39 },
        {  40 },
        {  41 },
        {  70,  68 },
        {   0 },
        {  42,  70, 202,  68 },
        {  43,  70, 203,  68 },
        {  72,  71 },
        {   0 },
        {  44,  72, 204,  71 },
        {  45,  72, 205,  71 },
        {  73,  74 },
        {   2, 206 },
        {   3, 207 },
        {   4, 208 },
        {   5 },
        {   6 },
        {   7, 209 },
        {  33,  59,  34 },
        {  42,  73 },
        {  43,  73, 210 },
        {   0 },
        {  29,  75 },
        {  22 },
        {  23 },
        {  24 }
    };

    String[] PARSER_ERROR =
    {
        "",
        "esperado EOF",
        "esperado identificador",
        "esperado constante_int",
        "esperado constante_float",
        "esperado constante_bin",
        "esperado constante_hexa",
        "esperado constante_str",
        "esperado bin",
        "esperado bool",
        "esperado elif",
        "esperado else",
        "esperado endif",
        "esperado false",
        "esperado float",
        "esperado hexa",
        "esperado if",
        "esperado int",
        "esperado input",
        "esperado main",
        "esperado output",
        "esperado str",
        "esperado toInt",
        "esperado toBin",
        "esperado toHexa",
        "esperado true",
        "esperado repeat",
        "esperado until",
        "esperado ,",
        "esperado .",
        "esperado ;",
        "esperado =",
        "esperado :",
        "esperado (",
        "esperado )",
        "esperado &",
        "esperado |",
        "esperado !",
        "esperado ==",
        "esperado !=",
        "esperado <",
        "esperado >",
        "esperado +",
        "esperado -",
        "esperado *",
        "esperado /",
        "esperado identificador  main", //"<programa> inválido",
        "esperado identificador  main", //"<dec_var> invalido",
        "esperado identificador if input output repeat ", //"<lista_comandos> invalido",
        "esperado identificador if input output repeat", //"<comando> invalido",
        "esperado identificador", //"<lista_variavel> invalido",
        "esperado identificador", //"<lista_id> invalido",
        "esperado constante_int constante_float constante_bin constante_hexa constante_str false true", //"<valor> invalido",
        "esperado bin bool float hexa int str", //"<tipo> invalido",
        "esperado identificador", //"<cmd_atribuicao> invalido",
        "esperado input", //"<cmd_ent_dados> invalido",
        "esperado output", // "<cmd_saida_dados> invalido",
        "esperado if", //"<cmd_sel> invalido",
        "esperado repeat", //"<cmd_rep> invalido",
        "esperado expressao", //"<expressao> invalido",
        "esperado expressao", //"<lista_exp> invalido",
        "esperado elif else endif", //"<cmd_sel_elif> invalido",
        "esperado else endif", //"<cmd_sel_else> invalido",
        "esperado expressao", //"<elemento> invalido",
        "esperado expressao", //"<expressao_> invalido",
        "esperado expressao", //"<relacional> invalido",
        "esperado expressao", //"<relacional_> invalido",
        "esperado expressao", //"<aritmetica> invalido",
        "esperado expressao", //"<aritmetica_> invalido",
        "esperado == != < >", //"<operador_relacional> invalido",
        "esperado expressao", //"<termo> invalido",
        "esperado expressao", //"<termo_> invalido",
        "esperado expressao", //"<fator> invalido",
        "esperado expressao", //"<membro> invalido",
        "esperado expressao", //"<membro_> invalido",
        "esperado expressao", //"<membro__> invalido",
        "esperado ; =", //<lista_variavel2> invalido",
        "esperado , = ) :", //"<lista_id2> invalido",
        "esperado EOF identificador elif else endif if input output repeat until", //"<lista_comandos2> invalido",
        "esperado , )", //"<lista_exp2> invalido"
    };
}
