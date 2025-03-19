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

    public boolean verificar()throws Exception{
        this.expressao = this.infixa.toUpperCase();
        char[] operadores = {'(',')','+','-','*','/','^'};
        byte parenteses = 0;
        boolean operador = false;
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
                if(!operador && ((int)this.expressao.charAt(i) < 65 || (int)this.expressao.charAt(i) > 90)){
                    throw new Exception();
                }
            }
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
            if(parenteses != 0){
                return false;
            }
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
            if(!aux.isEmpty()){
                if(aux.topo().equals("*") || aux.topo().equals("/")){
                    topo = 1;
                }else if(aux.topo().equals("+") || aux.topo().equals("-")){
                    topo = 0;
                }
            }
            if(textoAux[i].equals("*") || textoAux[i].equals("/")){
                elemento = 1;
                operador = true;
            }else if(textoAux[i].equals("+") || textoAux[i].equals("-")){
                elemento = 0;
                operador = true;
            }else if(textoAux[i].equals("(") || textoAux[i].equals("^")){
                aux.push(textoAux[i]);
            }else if(textoAux[i].equals(")")){
                do {
                    this.posfixa += aux.pop();
                }while (!aux.topo().equals("("));{}
                aux.pop();
            }else if(elemento == -1){
                this.posfixa += textoAux[i];
            }
            if(operador){
                if(topo > elemento){
                    this.posfixa += aux.pop();
                    aux.push(textoAux[i]);
                }else{
                    aux.push(textoAux[i]);
                }
            }
        }
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
            if(!operador){
                try {
                    aux.push("" + repl.valores(posfixa[i]));
                }catch(Exception e){
                    excessao = true;
                }
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
        return aux.pop();
    }
}