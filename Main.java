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
        String[] invalidos = {"REC","PLAY","ERASE","EXIT"};
        while (true) {
            verificacao = scanner.nextLine();
            if(gravando && gravados<11){
                boolean invalido = false;
                for(int i=0;i<4;i++){
                    if(verificacao.equalsIgnoreCase(invalidos[i])){
                        System.out.println("Erro: comando inválido para gravação");
                        invalido = true;
                    }
                }
                if (verificacao.equalsIgnoreCase("STOP")) {
                    System.out.println("Encerrando gravação... (REC: " + gravados + "/10)");
                    gravando = false;
                    invalido = true;
                }
                if(!invalido){
                    repl.Rec(verificacao, gravados);
                    System.out.println("(REC: " + gravados + "/10) " + verificacao);
                    gravados++;
                }
            }else{
                verificacao = verificacao.replaceAll(" ","");
                avaliador.setExpressao(verificacao);
                if(verificacao.charAt(1) == '='){
                    repl.escrever(verificacao);
                }else if(verificacao.equalsIgnoreCase("REC")){
                    System.out.println("Iniciando gravação... (REC: 0/10)");
                    gravando = true;
                }else if(verificacao.equalsIgnoreCase("VARS")){repl.Vars();
                }else if(verificacao.equalsIgnoreCase("EXIT")){
                    scanner.close();
                    return;
                }else if(avaliador.verificar()){
                    try{
                        System.out.println(avaliador.resolver());
                    } catch (Exception e){
                        System.out.println("Excessao");
                        scanner.close();
                        return;
                    }
                }else{System.out.println("Expressao invalida!");}
            }
        }
    }
}