package eventi;

//import java.util.ArrayList;
//import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;

public class Eventi {

    private ConcurrentHashMap<String, Evento> listaEventi = new ConcurrentHashMap<>();

    public synchronized void Crea(String nome, int posti) {
        Evento nuovoEvento = new Evento(nome, posti);
        listaEventi.putIfAbsent(nome, nuovoEvento);

        System.out.println("Ho creato l'evento " + nome + " con " + posti + " posti");

    }

    public synchronized void Aggiungi(String nome, int posti) {
        Evento evento = listaEventi.get(nome);
        if (evento != null) {
            evento.AggiungiPosti(posti);
            notify();
            System.out.println("Ho aggiunto " + posti + " posti all'evento " + nome);
        } else {
            throw new IllegalArgumentException("Non puoi aggiungere posti a questo evento poichè non esiste");
        }
    }

    public void ListaEventi() {
        if (listaEventi.isEmpty()) {
            System.out.println("La lista di eventi è vuota");
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

    public void Chiudi(String nome) {
        listaEventi.remove(nome);
        System.out.println("Ho chiuso l'evento " + nome);
    }

    public synchronized void Prenota(String nome, int posti) { // non può essere synchronized altrimenti quando va in wait blocca tutti gli altri processi

        boolean isItPrinted = false; // per stampare una volta sola il messaggio di waiting

        if (!listaEventi.containsKey(nome)) {
            throw new IllegalArgumentException("Non puoi prenotare questo evento poichè o non esiste o è stato già chiuso");
        }

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
        evento.Prenota(posti);
        System.out.println("Ho prenotato " + posti + " posti per l'evento " + nome);
    }

}
