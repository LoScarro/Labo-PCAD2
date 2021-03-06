package eventi;

//import java.util.ArrayList;
//import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;

public class Eventi {

    private ConcurrentHashMap<String, Evento> listaEventi = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> controllo = new ConcurrentHashMap<>();//usata per verificare che le operzioni siano andate a buon fine

    public ConcurrentHashMap<String, Integer> getControllo() {
        return controllo;
    }
    
    public void Crea(String nome, int posti) {
        
        Evento nuovoEvento = new Evento(nome, posti);
        listaEventi.putIfAbsent(nome, nuovoEvento);
        controllo.putIfAbsent(nome, posti);

        System.out.println("Ho creato l'evento " + nome + " con " + posti + " posti");

    }

    public synchronized void Aggiungi(String nome, int posti) {

        Evento evento = listaEventi.get(nome);
        
        if (evento != null) {
            evento.AggiungiPosti(posti);
            controllo.replace(nome, controllo.get(nome) + posti);
            notify();
            System.out.println("Ho aggiunto " + posti + " posti all'evento " + nome);
        } else {
            throw new IllegalArgumentException("Non puoi aggiungere posti a questo evento poichè non esiste");
        }
    }

    public void ListaEventi() {
        
        if (listaEventi.isEmpty()) {
            System.out.println("La lista di eventi è vuota, ha funzionato tutto!");
        } else {
            int i = 1;
            
            for (String evento : listaEventi.keySet()) {
                System.out.println(
                        i + ") L'evento " + evento + " ha ancora " + listaEventi.get(evento).getPostiLiberi()
                                + " posti liberi.");
                i++;
            
            }
        }
    }

    // Prima di chiudere un evento controllo se i posti liberi corrispondono a quelli contenuti nella hashmap "controllo"
    public synchronized void Chiudi(String nome) {
        assert controllo.get(nome) == listaEventi.get(nome).getPostiLiberi(): "I posti disponibili dell'evento " + nome + " non corrispondono!";
        listaEventi.remove(nome);
        notifyAll();
        System.out.println("Ho chiuso l'evento " + nome);
    }

    public int getPosti(String nome) {
        return listaEventi.get(nome).getPostiLiberi();
    }

    public synchronized void Prenota(String nome, int posti) {
        boolean isItPrinted = false; // per stampare una volta sola il messaggio di waiting
        isItPrinted = false;
        
        while (listaEventi.get(nome).getPostiLiberi() < posti) {
            try {
                wait();
                if (isItPrinted == false) {
                    System.out.println("Sto aspettando che vengano aggiunti dei posti all'evento " + nome);
                    isItPrinted = true;
                }
            } catch (Exception e) {
            }
        }
        
        Evento evento = listaEventi.get(nome);
        
        if(evento==null){
            throw new IllegalArgumentException("Non puoi prenotare l'evento " + nome + " poichè o non esiste o è stato già chiuso");
        }
        
        evento.Prenota(posti);
        controllo.replace(nome, controllo.get(nome) - posti);
        System.out.println("Ho prenotato " + posti + " posti per l'evento " + nome);
    }

}
