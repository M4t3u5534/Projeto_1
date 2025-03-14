public class Avaliador {
    private String infixa;
    private String posfixa = "";
    private String expressao = "";

    public Avaliador(){
        this.infixa = "";
    }

    public void setExpressao(String expressao) {
        this.infixa = expressao;
    }

    public boolean verificar(){
        this.expressao = this.infixa.toUpperCase();
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
        return this.posfixa;
    }
}
