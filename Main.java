// Carolina Lee - 10440304
// Mateus Ribeiro Cerqueira - 10443901
//Pedro Carvalho - 10418861
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String verificacao = "";
        while(verificacao != "EXIT"){
            verificacao = scanner.nextLine();
        }
        if(!verificar(verificacao)){
            System.out.println("Expressao invalida!");
            scanner.close();
            return;
        }
        scanner.close();
    }

    public static boolean verificar(String expressao){
        expressao = expressao.replaceAll(" ", "");
        expressao = expressao.toUpperCase();
        char[] operadores = {'(',')','+','-','*','/','^'};
        byte parenteses = 0;
        for(byte i=0;i<expressao.length();i++){
            for(byte j=0;j<6;j++){
                if(!(expressao.charAt(i) == operadores[j] || expressao.charAt(i+1) == operadores[j])){
                    return false;
                }

                if(expressao.charAt(i) == '(')parenteses++;
                else if(expressao.charAt(i) == ')')parenteses--;

                if(expressao.charAt(i) != operadores[j] && ((int)expressao.charAt(i) < 65 || (int)expressao.charAt(i) > 90)){
                    return false;
                }
            }
        }
        if(parenteses != 0){
            return false;
        }
        return true;
    }
}