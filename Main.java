// Carolina Lee - 10440304
// Mateus Ribeiro Cerqueira - 10443901
//Pedro Carvalho - 10418861
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String verificacao = "";
        Avaliador avaliador = new Avaliador();
        while (!verificacao.equalsIgnoreCase("EXIT")) {
            verificacao = scanner.nextLine();
            avaliador.setExpressao(verificacao);
            if(!avaliador.verificar()){
                System.out.println("Expressao invalida!");
                scanner.close();
                return;
            }
        }
        try{
            System.out.println(avaliador.converter());
            scanner.close();
        } catch (Exception e){
            System.out.println("Excessao");
            scanner.close();
        }
    }
}