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
        boolean operador = false;
        if((int)expressao.charAt(0) < 65 || (int)expressao.charAt(0) > 90){
            operador = true;
        }
        String[] textoAux = expressao.split("");
        boolean inicioPilha = false;
        if(operador){
            aux.push(textoAux[0]);
            inicioPilha = true;
        }
        byte topo = -1;
        byte elemento = -1;
        for(byte i=0;i<expressao.length();i++){
            if(inicioPilha){
                if(aux.topo() == "*" || aux.topo() == "/"){
                    topo = 1;
                }else if(aux.topo() == "+" || aux.topo() == "-"){
                    topo = 0;
                }
            }
            if(textoAux[i] == "*" || textoAux[i] == "/"){
                elemento = 1;
            }else if(textoAux[i] == "+" || textoAux[i] == "-"){
                elemento = 0;
            }else if(textoAux[i].equals("(") || textoAux[i].equals("^")){
                aux.push(textoAux[i]);
            }else if(textoAux[i].equals(")")){
                do {
                    saida += aux.pop();
                }while (aux.topo() != "(");{}
                aux.pop();
            }else if(elemento == -1){
                saida += textoAux[i];
            }else if(topo > elemento){
                    aux.push(textoAux[i]);
            }else{
                saida += aux.pop();
                aux.push(textoAux[i]);
            }
        }
        while(!aux.isEmpty()){
            saida += aux.pop();
        }
        return saida;
    }
}