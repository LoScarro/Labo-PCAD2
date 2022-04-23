package eventi;

import java.util.concurrent.*;

public class Evento{
    private String nome;
    private int postiLiberi;
    private BlockingQueue<String> coda;
    
    public Evento(String nome, int postiLiberi){
        this.nome=nome;
        this.postiLiberi=postiLiberi;
        this.coda=new LinkedBlockingDeque<>();
    }

    public void AggiungiAcoda(String persona){
        this.coda.add(persona);
    }

    public void AggiungiPosti(int posti){
        this.postiLiberi+=posti;
    }

    public String getName(){
        return this.nome;
    }
    
    public int getPostiLiberi(){
        return this.postiLiberi;
    }
    
    public void Prenota(String id){
        if(coda.isEmpty()){
            postiLiberi--;
        }
        else{
            coda.add(id);
        }
    }
}