public class Repl {
    private String[] dicionario = new String[50];
    private byte indice = 0;
    Pilha gravacao = new Pilha();

    public void escrever(String variavel){
        variavel = variavel.toUpperCase();
        String[] aux = variavel.split("=");
        for(int i=0;i<2;i++){
            this.dicionario[this.indice] = aux[i];
            this.indice++;
        }
        return;
    }

    public int valores(String variavel){
        for(int i=0;i<this.indice;i+=2){
            if (variavel.equals(this.dicionario[i])) {
                return Integer.parseInt(this.dicionario[i+1]);
            }
        }
        System.out.println("Variavel " + variavel + " nao definida!");
        return -1;
    }

    public void Vars(){
        boolean loop = false;
        for(int i=0;i<indice;i+=2){
            System.out.println(dicionario[i] + " = " + dicionario[i+1]);
            loop = true;
        }
        if(!loop)System.out.println("Nenhuma variavel definida");
    }

    public void Reset(){
        this.indice = 0;
        System.out.println("Variaveis reiniciadas");
    }

    public void Rec(String digitado, int gravados){
        try {
            gravacao.push(digitado);
        } catch (Exception e) {}
    }
}