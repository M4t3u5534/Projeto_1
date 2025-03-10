public class Pilha {
    private int TAM_PILHA = 50;
    private int topoPilha;
    private String pilha[];

    public Pilha(){
        this.pilha = new String[TAM_PILHA];
        this.topoPilha = -1;
    }

    public boolean isEmpty() {return this.topoPilha == -1;}
    
    public boolean isFull() {return this.topoPilha == this.pilha.length-1;}

    public void push(String i) throws Exception{
        if (! this.isFull( ))
        this.pilha[++this.topoPilha] = i;
        else
        throw new Exception("overflow - Estouro de Pilha");
    }

    public String pop() throws Exception{
        if (! this.isEmpty( ))
        return this.pilha[this.topoPilha--];
        else{
        throw new Exception( "underflow - Esvaziamento de Pilha");
        }
    }

    public String topo() throws Exception{
        if ( ! this.isEmpty( ))
        return this.pilha[this.topoPilha];
        else{
        throw new Exception("Underlow - Esvaziamento de Pilha");
        }
    }
    
    public int sizeElements() {return topoPilha+1;}
}
