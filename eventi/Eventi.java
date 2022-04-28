package eventi;

//import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Eventi {
    private ArrayBlockingQueue<Evento> listaEventi = new ArrayBlockingQueue<Evento>(10);

    public synchronized void Crea(String nome, int posti) {
        
            if (!isIn(nome)) {
                Evento nuovoEvento = new Evento(nome, posti);
                listaEventi.add(nuovoEvento);
            } else
                throw new IllegalArgumentException("Errore: l'evento esiste gia'!");
    }

    public synchronized void Aggiungi(String nome, int posti) {
        
            Evento evento = getEvento(nome);
            if (evento != null) {
                evento.AggiungiPosti(posti);
            } else {
                throw new IllegalArgumentException();
            }
        
    }

    public synchronized void ListaEventi() {
        
            if (listaEventi.isEmpty()) {
                System.out.println("La lista di eventi è vuota");
            }
            int i = 1;
            for (Evento evento : listaEventi) {
                System.out.println(
                        i + ") L'evento " + evento.getName() + " ha ancora " + evento.getPostiLiberi() + " posti liberi.");
                        i++;
            }
        
    }

    private synchronized Evento getEvento(String nome) {
        for (Evento evento : listaEventi) {
            if (evento.getName().equals(nome)) {
                return evento;
            }
        }
        return null;
    }

    private synchronized boolean isIn(String nome) {
            for (Evento evento : listaEventi) {
                if (evento.getName().equals(nome)) {
                    return true;
                }
            }
            return false;
    }

    public synchronized void Chiudi(String nome) {
        if (isIn(nome)) {
            Evento evento = getEvento(nome);
            listaEventi.remove(evento);
        } else
            throw new IllegalArgumentException();
    }

    public synchronized void Prenota(String nome, int posti) {
        if (!isIn(nome)) {
            throw new IllegalArgumentException();
        }
        Evento evento = getEvento(nome);
        evento.Prenota(posti);
    }

}
