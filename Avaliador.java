// Carolina Lee - 10440304
// Mateus Ribeiro Cerqueira - 10443901
//Pedro Carvalho - 10418861
public class Avaliador {
    private String infixa;
    private String posfixa;
    private String expressao = "";

    public Avaliador(){
        this.infixa = "";
    }

    public void setExpressao(String expressao) {
        this.infixa = expressao;
    }

    /* Método que verifica se a expressão é valida ou não
     */
    public boolean verificar()throws Exception{
        this.expressao = this.infixa.toUpperCase();
        char[] operadores = {'(',')','+','-','*','/','^'};
        byte parenteses = 0;
        boolean operador = false;
        /* Caso a expressão possua número par de digitos ela é inválida
         */
        if(this.expressao.length() % 2 == 0){
            return false;
        }
        else{
            for(byte i=0;i<this.expressao.length();i++){
                operador = false;
                for(byte j=0;j<7;j++){
                    if(this.expressao.charAt(i) == operadores[j]){
                        operador = true;
                        break;
                    }
                }
                if(this.expressao.charAt(i) == '(')parenteses++;
                else if(this.expressao.charAt(i) == ')')parenteses--;
                /* Caso a expressão possua um digito que não seja uma variável ou um operador ela é inválida.
                 * Joga uma excessão para identificar que o motivo da expressão ser inválida é um operador inválido. 
                 */
                if(!operador && ((int)this.expressao.charAt(i) < 65 || (int)this.expressao.charAt(i) > 90)){
                    throw new Exception();
                }
            }
            /* Verifica se a cada dois digitos existe pelo menos um operador, caso contrário a expressão é inválida
             */
            for(byte i=1;i<this.expressao.length();i+=2){
                operador = false;
                for(byte j=0;j<7;j++){
                    if(this.expressao.charAt(i-1) == operadores[j]){
                        operador = true;
                        break;
                    }else if(i<this.expressao.length()-1){
                        if(this.expressao.charAt(i) == operadores[j]){
                            operador = true;
                            break;
                        }
                    }
                }
                if(!operador){
                    return false;
                }
            }
            /* Se a quantidade de parênteses for incoerente a expressão é inválida
             */
            if(parenteses != 0){
                return false;
            }
            /* Caso a expressão não se encaixe em nenhum dos casos ela é válida
             */
            return true;
        }
    }

    public void converter()throws Exception{
        Pilha aux = new Pilha();
        this.posfixa = "";
        String[] textoAux = this.expressao.split("");
        byte topo = -1;
        for(byte i=0;i<this.expressao.length();i++){
            byte elemento = -1;
            boolean operador = false;
            /* Se a pilha auxiliar não estiver vazia, verifica se o topo da pilha é um operador de importância maior(1) ou menor(0)
             */
            if(!aux.isEmpty()){
                if(aux.topo().equals("*") || aux.topo().equals("/")){
                    topo = 1;
                }else if(aux.topo().equals("+") || aux.topo().equals("-")){
                    topo = 0;
                }
            }
            /* Cadeia de if(s) e else(s) que verifica se o operador 'i' da expressão:
             * 1- Possui importância máxima('^' ou '(');
             * 2- Possui alta importância('*' ou '/');
             * 3- Possui baixa importância('+' ou '-');
             * 4- É um fecha parênteses;
             * 5- Não é um operador.
             */
            if(textoAux[i].equals("*") || textoAux[i].equals("/")){
                elemento = 1;
                operador = true;
            }else if(textoAux[i].equals("+") || textoAux[i].equals("-")){
                elemento = 0;
                operador = true;
            /* Caso o operador 'i' se encontre no caso 1, coloca 'i' na pilha auxiliar
             */
            }else if(textoAux[i].equals("(") || textoAux[i].equals("^")){
                aux.push(textoAux[i]);
            /* Caso o operador 'i' se encontre no caso 4,
             * tira objetos da pilha auxiliar e adiciona-os a expressão convertida(posfixa) até encontrar um abre parênteses.
             */
            }else if(textoAux[i].equals(")")){
                do {
                    this.posfixa += aux.pop();
                }while (!aux.topo().equals("("));{}
                aux.pop();
            /* Caso o operador 'i' se encontre no caso 5, adiciona 'i' na expressão convertida.
             */
            }else if(elemento == -1){
                this.posfixa += textoAux[i];
            }
            /* Caso o operador 'i' se encontre no caso 2 ou 3, verifica qual operador é 
             * de maior importância(o operador da pilha ou o operador da expressão),
             * caso o operador da pilha seja o de maior importância, retira o operador da pilha e adiciona-o a expressão convertida,
             * depois adiciona o operador da expressão na pilha. Caso contrário, adiciona o operador da expressão no topo da pilha.
             */
            if(operador){
                if(topo > elemento){
                    this.posfixa += aux.pop();
                    aux.push(textoAux[i]);
                }else{
                    aux.push(textoAux[i]);
                }
            }
        }
        /* Após toda a lógica de conversão, caso ainda haja operadores na pilha retira-os e adiciona-os no fim da expressão convertida.
         */
        while(!aux.isEmpty()){
            this.posfixa += aux.pop();
        }
    }

    public String resolver(Repl repl)throws Exception{
        boolean excessao = false;
        Pilha aux = new Pilha();
        converter();
        String[] posfixa = this.posfixa.split("");
        String[] operadores = {"+","-","*","/","^"};
        boolean operador;
        for(int i=0;i<this.posfixa.length();i++){
            operador = false;
            for(int j=0;j<5;j++){
                if(posfixa[i].equals(operadores[j])){
                    operador = true;
                    break;
                }
            }
            /* Caso o digito da expressão convertida seja uma varíavel(tudo que não é um operador),
             * adiciona o valor dessa variável a pilha auxiliar criada acima.
             */
            if(!operador){
                try {
                    aux.push("" + repl.valores(posfixa[i]));
                }catch(Exception e){
                    excessao = true;
                }
            /* Caso contrário verifica qual operador o digito da expressão é e resolve a operação.
             */
            }else{
                if(posfixa[i].equals("+")){
                    aux.push("" + (Double.parseDouble(aux.pop()) + Double.parseDouble(aux.pop())));
                }else if(posfixa[i].equals("-")){
                    double subtrator = Double.parseDouble(aux.pop());
                    aux.push("" + (Double.parseDouble(aux.pop()) - subtrator));
                }else if(posfixa[i].equals("*")){
                    aux.push("" + (Double.parseDouble(aux.pop()) * Double.parseDouble(aux.pop())));
                }else if(posfixa[i].equals("^")){
                    double expoente = Double.parseDouble(aux.pop());
                    aux.push("" + (Math.pow(Double.parseDouble(aux.pop()),expoente)));
                }else{
                    double divisor = Double.parseDouble(aux.pop());
                    aux.push("" + (Double.parseDouble(aux.pop()) / divisor));
                }
            }
        }
        if(excessao){
            throw new Exception();
        }
        /* Retorna o resultado da expressão que está armazenado na pilha auxiliar.
         */
        return aux.pop();
    }
}