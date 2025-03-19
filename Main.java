// Carolina Lee - 10440304
// Mateus Ribeiro Cerqueira - 10443901
//Pedro Carvalho - 10418861
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String verificacao = "";
        Avaliador avaliador = new Avaliador();
        Repl repl = new Repl();
        int gravados = 1;
        boolean gravando = false;
        boolean verificado = false;
        String[] invalidos = {"REC","PLAY","ERASE","EXIT"};
        while (true) {
            verificacao = scanner.nextLine();
            /* Se o programa estiver em estado de gravação(REC), é executado apenas o if abaixo, gravando o que é escrito,
             * informando o que é invalido para a gravação e verificando se o usuário digitou 'STOP' para finalizar a gravação.
            */
            if(gravando && gravados<11){
                boolean invalido = false;
                for(int i=0;i<4;i++){
                    if(verificacao.equalsIgnoreCase(invalidos[i])){
                        System.out.println("Erro: comando inválido para gravação");
                        invalido = true;
                    }
                }
                if (verificacao.equalsIgnoreCase("STOP")) {
                    System.out.println("Encerrando gravação... (REC: " + --gravados + "/10)");
                    gravando = false;
                    invalido = true;
                    gravados = 1;
                }
                if(!invalido){
                    repl.rec(verificacao);
                    System.out.println("(REC: " + gravados + "/10) " + verificacao);
                    gravados++;
                }
            /* Caso o programa não estiver em estado de gravação(REC),
             * ele lê o texto digitado e executa os comandos necessários normalmente.
            */
            }else{
                verificacao = verificacao.replaceAll(" ","");
                avaliador.setExpressao(verificacao);
                /* Cadeia de if(s) e else(s) para verificar em qual caso o texto digitado pelo usuário se encaixa.
                 */
                if(verificacao.length() == 1){
                    try {
                        System.out.println(repl.valores(verificacao));
                    } catch (Exception e) {}
                }else if(verificacao.charAt(1) == '='){
                    String[] declaracao = new String[2];
                    declaracao = verificacao.split("=");
                    System.out.println(declaracao[0] + " " + "=" + " " +declaracao[1]);
                    repl.escrever(verificacao);
                }else if(verificacao.equalsIgnoreCase("REC")){
                    System.out.println("Iniciando gravação... (REC: 0/10)");
                    gravando = true;
                }else if(verificacao.equalsIgnoreCase("VARS")){repl.vars();
                }else if(verificacao.equalsIgnoreCase("RESET")){repl.reset();
                }else if(verificacao.equalsIgnoreCase("ERASE")){
                    repl.apagar();
                    gravados = 0;
                    System.out.println("Gravação apagada.");
                }else if(verificacao.equalsIgnoreCase("PLAY")){repl.play();
                }else if(verificacao.equalsIgnoreCase("EXIT")){
                    scanner.close();
                    return;
                }else{
                    try {
                        verificado = avaliador.verificar();
                    } catch (Exception e) {
                        System.out.println("Erro: operador inválido.");
                        continue;
                    }
                    if(verificado){
                        try{
                            System.out.println(avaliador.resolver(repl));
                        } catch (Exception e){}
                    }else{System.out.println("Expressao invalida!");}
                }
            }
        }
    }
}