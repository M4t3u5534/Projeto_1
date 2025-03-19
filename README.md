# Projeto_1 - Avaliador de expressões matemáticas
## Atividade:
### Desenvolver um programa em Java que implementa um REPL (Read-Evaluate-Print-Loop) que aceita entradas via teclado e que avalia e valida as entradas de acordo com os requisitos descritos a seguir:
#### _Expressões matemáticas_
- #### Devem conter somente variáveis e operadores (ver abaixo).
- #### Podem conter espaços em branco, assim como não ter espaços em branco entre variáveis e operadores.
- #### Devem estar em notação infixa.
- #### Devem ser convertidas para a notação posfixa.
- #### O cálculo da expressão deve ser realizado usando a expressão posfixa, somente se todas as variáveis presentes na expressão possuem valores definidos.
- #### O programa deve validar as expressões matemáticas, isto é:
  - #### Aceitar somente as operações indicadas abaixo.
  - #### Aceitar somente variáveis como operandos, sendo que as variáveis possuem uma única letra, conforme indicado abaixo.
  - #### Considerar que uma expressão matemática na notação infixa pode conter parênteses que definem a prioridade das operações
- #### Caso a expressão inserida seja inválida (por exemplo, a expressão contém algum operador que não seja um dos cinco indicados ou possui uma quantidade incorreta de parênteses, o programa deve exibir uma mensagem informando o erro.
#### _Operações suportadas_
- #### Binárias: + - * / ^ (adição, subtração, multiplicação, divisão e exponenciação, respectivamente).
- #### Parênteses: ( ).
#### _Variáveis_
- #### A-Z, case insensitive.
## _REPL: Comandos válidos (case insensitive)_
- #### Expressão matemática infixa - Após validação, exibe o resultado em tela.
- #### VAR = VALUE - Atribui um valor para uma variável, sendo: VAR: Variável (A-Z). VALUE: Número real.
- #### VARS - Lista somente as variáveis com valores definidos e seus respectivos valores.
- #### RESET - Reinicia todas as variáveis.
- #### REC - Começa a gravar (inserir) os comandos digitados em uma fila. A fila deve possuir um limite de 10 comandos. Caso o limite seja atingido, o comando REC é parado automaticamente. O programa deve mostrar quantos comandos ainda podem ser gravados na fila. No modo REC, os comandos inseridos não devem ser executados, apenas gravados na fila
- #### STOP - Para de gravar os comandos.
- #### PLAY - Reproduz os comandos gravados na fila, na sequência correta. A execução do comando PLAY não deve perder o conteúdo gravado na fila.
- #### ERASE - Apaga todos os comandos da fila.
- #### EXIT - Encerra o programa.
#### Quando estiver em modo REC (gravação), somente os cinco primeiros comandos da tabela acima são válidos. Ou seja, comandos relacionados à fila (REC, STOP, PLAY, ERASE) e encerramento do programa não são aceitos para gravação (não podem ser inseridos na fila de comandos).
## Nomes: Mateus Ribeiro Cerqueira - RA: 10443901 
##        Carolina Lee - RA: 10440304
##        Pedro Carvalho - RA: 10418861
