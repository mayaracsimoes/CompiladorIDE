# Compilador

Este é um projeto de desenvolvimento de um compilador realizado durante a disciplina de Compiladores 2024/1 da FURB
Alunas: Brenda Eising e Mayara Cardoso

## Visão Geral

O objetivo deste projeto é criar um compilador capaz de traduzir código-fonte escrito em nossa linguagem de programação especificada na disciplina para código de máquina executável.

O compilador será dividido em várias etapas, incluindo análise léxica, análise sintática, análise semântica e geração de código intermediário.

## Especificação da linguagem

Léxico: 
identificador: {letras} ({letras} | {numeros})* (_({letras}|{numeros})+)* _?
constante_int: [1-9] ({numeros})* | 0
constante_float: ([1-9] ({numeros})* | 0) \. ({numeros}*[1-9] | 0)
constante_bin: #b(0|1)+
constante_hexa: #h ({numeros}|{hexa})+
constante_str: \" [^\" \\ \n]* \"

palavras reservadas:

pr_bin = identificador:"bin"
pr_bool = identificador:"bool"
pr_elif = identificador:"elif"
pr_else = identificador:"else"
pr_endif = identificador:"endif"
pr_false = identificador:"false"
pr_float = identificador:"float"
pr_hexa = identificador:"hexa"
pr_if = identificador:"if"
pr_int = identificador:"int"
pr_input = identificador:"input"
pr_main = identificador:"main"
pr_output = identificador:"output"
pr_str = identificador:"str"
pr_toInt = identificador:"toInt"
pr_toBin = identificador:"toBin"
pr_toHexa = identificador:"toHexa"
pr_true = identificador:"true"
pr_repeat = identificador:"repeat"
pr_until = identificador:"until"

//simbolos especiais
, . ; = : ( ) & | ! == != < > + - * /

// caracteres de formação
:[\s\n\t]

comentários:
//linha
:! \?([^\n])*

//bloco
:! "!" \- \- ([^\-"!"] | (\-\-)+[^"!"] | ("!")+[^\-])* \-\- "!"

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
