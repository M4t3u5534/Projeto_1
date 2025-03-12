public class Avaliador {
    private String infixa;
    private String posfixa;
    private String expressao;
    private String[] dicionario = new String[50];
    private byte indice = 0;

    public Avaliador(String expressao){
        this.infixa = expressao;
    }

    public Avaliador(){
        this("");
    }

    public void setExpressao(String expressao) {
        this.infixa = expressao;
    }

    public void escrever(String variavel){
        variavel = variavel.replaceAll(" ","");
        String[] aux = variavel.split("=");
        for(int i=0;i<2;i++){
            this.dicionario[this.indice] = aux[i];
            this.indice++;
        }
        return;
    }

    public int valores(String variavel)throws Exception{
        for(int i=0;i<this.indice;i+=2){
            if (variavel.equals(this.dicionario[i])) {
                return Integer.parseInt(this.dicionario[i+1]);
            }
        }
        throw new Exception("Variavel nao definida!");
    }

    public boolean verificar(){
        this.expressao = this.infixa.replaceAll(" ", "");
        this.expressao = this.expressao.toUpperCase();
        char[] operadores = {'(',')','+','-','*','/','^'};
        byte parenteses = 0;
        boolean operador = false;
        for(byte i=0;i<this.expressao.length();i++){
            for(byte j=0;j<6;j++){
                if(this.expressao.charAt(i) == operadores[j]){
                    operador = true;
                    break;
                }
            }
            if(this.expressao.charAt(i) == '(')parenteses++;
            else if(this.expressao.charAt(i) == ')')parenteses--;
            if(!operador && ((int)this.expressao.charAt(i) < 65 || (int)this.expressao.charAt(i) > 90)){
                return false;
            }
        }
            for(byte i=1;i<this.expressao.length()+1;i++){
                for(byte j=0;j<6;j++){
                    if(this.expressao.charAt(i-1) == operadores[j]){
                        operador = true;
                        break;
                    }
                }
                if(i % 2 == 0 && !operador){
                    return false;
                }
            }
        if(parenteses != 0){
            return false;
        }
        return true;
    }

    public String converter()throws Exception{
        Pilha aux = new Pilha();
        String[] textoAux = this.expressao.split("");
        byte topo = 2;
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
                    aux.push(textoAux[i]);
                }else{
                    this.posfixa += aux.pop();
                    aux.push(textoAux[i]);
                }
            }
        }
        while(!aux.isEmpty()){
            this.posfixa += aux.pop();
        }
        return this.posfixa;
    }
}
