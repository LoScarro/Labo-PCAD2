package eventi;

//import java.util.ArrayList;
//import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;

public class Eventi {

    private ConcurrentHashMap<String, Evento> listaEventi = new ConcurrentHashMap<>();

    public synchronized void Crea(String nome, int posti) {
        synchronized (listaEventi) {
            Evento nuovoEvento = new Evento(nome, posti);
            listaEventi.putIfAbsent(nome, nuovoEvento);
            listaEventi.notifyAll();
            System.out.println("Ho creato l'evento " + nome + " con " + posti + " posti");
        }
    }

    public synchronized void Aggiungi(String nome, int posti) {
        synchronized (listaEventi) {
            Evento evento = listaEventi.get(nome);
            if (evento != null) {
                evento.AggiungiPosti(posti);
                listaEventi.notifyAll();
                System.out.println("Ho aggiunto " + posti + " posti all'evento " + nome);
            } else {
                throw new IllegalArgumentException("Non puoi aggiungere posti a questo evento poichè non esiste");
            }
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
        synchronized (listaEventi) {
            listaEventi.remove(nome);
            Evento evento = listaEventi.get(nome);
            evento.chiudi();
            listaEventi.notifyAll();
        }
    }

    public synchronized void Prenota(String nome, int posti) { // non può essere synchronized altrimenti quando va in wait blocca tutti gli altri processi
        
        boolean isItPrinted = false;        //per stampare una volta sola il messaggio di waiting

        while (!listaEventi.containsKey(nome)) {
            try {
                listaEventi.wait();
            } catch (Exception e) {
                if (isItPrinted == false) {
                    System.out.println("Sto aspettando che venga creato l'evento " + nome);
                    isItPrinted = true;
                }
            }
        }

        isItPrinted = false;

        while (listaEventi.get(nome).getPostiLiberi()<posti) {
            try {
                listaEventi.wait();
            }
            catch (Exception e) {
                if (isItPrinted == false) {
                    System.out.println("Sto aspettando che vengano aggiunti dei posti all'evento " + nome);
                    isItPrinted = true;
                }
            }
        }
        Evento evento = listaEventi.get(nome);
        evento.Prenota(posti);
        System.out.println("Ho prenotato " + posti + " posti per l'evento " + nome);
    }

}
