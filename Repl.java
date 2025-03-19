// Carolina Lee - 10440304
// Mateus Ribeiro Cerqueira - 10443901
//Pedro Carvalho - 10418861
public class Repl {
    private String[] dicionario = new String[50];
    private byte indice = 0;
    private Pilha gravacao = new Pilha();
    private String[] gravacaoAux = new String[50];
    private int tamanhoGravacao = 0;

    /* Escreve a variável e seu valor no dicionário criado acima.
     */
    public void escrever(String variavel){
        variavel = variavel.toUpperCase();
        String[] aux = variavel.split("=");
        for(int i=0;i<2;i++){
            this.dicionario[this.indice] = aux[i];
            this.indice++;
        }
        return;
    }

    /* Consulta o dicionário e retorna o valor da variável desejada, ou escreve que a variável em quastão não está definida. 
     */
    public double valores(String variavel)throws Exception{
        variavel = variavel.toUpperCase();
        for(int i=0;i<this.indice;i+=2){
            if (variavel.equals(this.dicionario[i])) {
                return (Double.parseDouble(this.dicionario[i+1]));
            }
        }
        System.out.println("Variavel " + variavel + " nao definida!");
        throw new Exception();
    }

    /* Escreve todas as variáveis definidas e seus respectivos valores, ou escreve que não há variáveis definidas.
     */
    public void vars(){
        boolean loop = false;
        for(int i=0;i<indice;i+=2){
            System.out.println(dicionario[i] + " = " + dicionario[i+1]);
            loop = true;
        }
        if(!loop)System.out.println("Nenhuma variavel definida");
    }

    /* Reinicia o dicionário removendo todas as variáveis.
     */
    public void reset(){
        this.indice = 0;
        System.out.println("Variaveis reiniciadas");
    }

    /* Grava o texto digitado(digitado) na pilha gravacao e uma cópia no vetor de string gravacaoAux.
     */
    public void rec(String digitado){
        try {
            gravacao.push(digitado);
            gravacaoAux[tamanhoGravacao] = digitado;
            tamanhoGravacao++;
        } catch (Exception e) {}
    }

    /* Apaga a gravação removendo todos elementos da pilha gravacao.
     */
    public void apagar(){
        try {
            for(int i=0;i<tamanhoGravacao;i++){
                gravacao.pop();
            }
        } catch (Exception e) {}
    }

    /* Executa os comandos gravados durante a gravação, mostrando-os antes de executá-los e informando caso não haja gravação.
     * Funciona de forma semelhante ao 'Main'.
     */
    public void play(){
        try {
            if(gravacao.isEmpty()){
                System.out.println("Não há gravação para ser reproduzida.");
            }
            else{
                System.out.println("Reproduzindo gravação...");
                Avaliador avaliador = new Avaliador();
                Repl repl = new Repl();
                String verificacao = "";
                String frase = "";
                for(int i=0;i<tamanhoGravacao;i++){
                    frase = gravacaoAux[i];
                    verificacao = frase.replaceAll(" ","");
                    avaliador.setExpressao(verificacao);
                    if(verificacao.equalsIgnoreCase("VARS")){repl.vars();
                    }else if(verificacao.equalsIgnoreCase("RESET")){repl.reset();
                    }else{
                        System.out.println(frase);
                        if(verificacao.length() == 1){
                            try {
                                System.out.println(repl.valores(verificacao));
                            } catch (Exception e) {}
                        }else if(verificacao.charAt(1) == '='){
                            repl.escrever(verificacao);
                        }else if(avaliador.verificar()){
                            try{
                                System.out.println(avaliador.resolver(repl));
                            } catch (Exception e){}
                        }else{System.out.println("Expressao invalida!");}
                    }
                }
            }
        }catch(Exception e){}
    }
}