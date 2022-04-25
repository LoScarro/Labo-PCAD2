package eventi;

import java.util.ArrayList;

public class Eventi {
    private ArrayList<Evento> listaEventi = new ArrayList<Evento>();
    
    public void Crea(String nome, int posti){
        if(!isIn(nome)){
            Evento nuovoEvento=new Evento(nome, posti);
            listaEventi.add(nuovoEvento);
        }
        else
        throw new IllegalArgumentException();
    }

    public void Aggiungi(String nome, int posti){
        Evento evento = getEvento(nome);
        if(evento!=null){
            evento.AggiungiPosti(posti);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public void ListaEventi(){
        if(listaEventi.isEmpty()){
            System.out.println("La lista di eventi è vuota");
        }
        for (Evento evento : listaEventi) {
            System.out.println("L'evento " + evento.getName() + " ha ancora " + evento.getPostiLiberi() + " posti liberi." );
        }
    }

    private Evento getEvento(String nome){
        for (Evento evento : listaEventi) {
            if (evento.getName().equals(nome)) {
                return evento;
            }
        }
        return null;
    }

    private boolean isIn(String nome){
        for (Evento evento : listaEventi) {
            if (evento.getName().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public void Chiudi(String nome){
        if(isIn(nome)){
            Evento evento = getEvento(nome);
            listaEventi.remove(evento);
        }
        else
            throw new IllegalArgumentException();
    }

    public void Prenota(String nome, int posti){
        if(!isIn(nome)){
            throw new IllegalArgumentException();
        }
        Evento evento = getEvento(nome);
        evento.Prenota(posti);
    }

}

