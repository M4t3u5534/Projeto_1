// Carolina Lee - 10440304
// Mateus Ribeiro Cerqueira - 10443901
//Pedro Carvalho - 10418861
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String verificacao = "";
        /*do{
            verificacao = scanner.nextLine();
        }
        while(!verificacao.equals("EXIT"));{}*/
        verificacao = scanner.nextLine();
        if(!verificar(verificacao)){
            System.out.println("Expressao invalida!");
            scanner.close();
            return;
        }
        try{
            String posfixa = Converter(verificacao);
            System.out.println(posfixa);
            scanner.close();
        } catch (Exception e){
            System.out.println("Excessao");
            scanner.close();
        }
    }

    public static boolean verificar(String expressao){
        expressao = expressao.replaceAll(" ", "");
        expressao = expressao.toUpperCase();
        char[] operadores = {'(',')','+','-','*','/','^'};
        byte parenteses = 0;
        boolean operador = false;
        for(byte i=0;i<expressao.length();i++){
            for(byte j=0;j<6;j++){
                if(expressao.charAt(i) == operadores[j]){
                    operador = true;
                    break;
                }
            }
            if(expressao.charAt(i) == '(')parenteses++;
            else if(expressao.charAt(i) == ')')parenteses--;
            if(!operador && ((int)expressao.charAt(i) < 65 || (int)expressao.charAt(i) > 90)){
                return false;
            }
        }
            for(byte i=1;i<expressao.length()+1;i++){
                for(byte j=0;j<6;j++){
                    if(expressao.charAt(i-1) == operadores[j]){
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

    public static String Converter(String expressao)throws Exception{
        expressao = expressao.replaceAll(" ", "");
        expressao = expressao.toUpperCase();
        String saida = "";
        Pilha aux = new Pilha();
        String[] textoAux = expressao.split("");
        byte topo = 2;
        for(byte i=0;i<expressao.length();i++){
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
                    saida += aux.pop();
                }while (!aux.topo().equals("("));{}
                aux.pop();
            }else if(elemento == -1){
                saida += textoAux[i];
            }
            if(operador){
                if(topo > elemento){
                    aux.push(textoAux[i]);
                }else{
                    saida += aux.pop();
                    aux.push(textoAux[i]);
                }
            }
        }
        while(!aux.isEmpty()){
            saida += aux.pop();
        }
        return saida;
    }
}