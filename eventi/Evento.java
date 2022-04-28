package eventi;


public class Evento{
    private String nome;
    private Integer postiLiberi;
    
    public Evento(String nome, int postiLiberi){
        this.nome=nome;
        this.postiLiberi=postiLiberi;
    }

    public synchronized void AggiungiPosti(int posti){
            this.postiLiberi+=posti;
            //postiLiberi.notifyAll();      Controllare per la concorrenza
    }

    public synchronized String getName(){
        return this.nome;
    }
    
    public synchronized int getPostiLiberi(){
        return this.postiLiberi;
    }
    
    public synchronized void Prenota(int posti){
            
            while(postiLiberi-posti<0) {
                try {
                postiLiberi.wait();
                }
                catch (Exception e){
                    System.out.println("Thread  interrupted.");
                }
            }
            postiLiberi-=posti;
    }
}