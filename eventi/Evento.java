package eventi;


public class Evento{
    private String nome;
    private Integer postiLiberi;
    
    public Evento(String nome, int postiLiberi){
        this.nome=nome;
        this.postiLiberi=postiLiberi;
    }

    public void AggiungiPosti(int posti){
        synchronized(postiLiberi){
            this.postiLiberi+=posti;
            //postiLiberi.notifyAll();      Controllare per la concorrenza
        }
    }

    public String getName(){
        return this.nome;
    }
    
    public int getPostiLiberi(){
        return this.postiLiberi;
    }
    
    public void Prenota(int posti){
        synchronized(postiLiberi){
            
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
}