import eventi.Eventi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String args[]){
        
        Eventi lista = new Eventi();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new Runnable(){
            public void run(){
                Admin(lista);
            }
        });
        
        executor.submit(new Runnable(){
            public void run(){
                Utente(lista);
            }
        });
        
        /*lista.Crea("Jova", 122);
        lista.ListaEventi();
        lista.Aggiungi("Jova", 22);
        lista.ListaEventi();
        lista.Chiudi("Jova");
        lista.ListaEventi();
        lista.Crea("Jova", 1222);
        lista.ListaEventi();
        lista.Prenota("Jova", 10);
        lista.ListaEventi();*/

    }
    public void Admin(Eventi evento){
        evento.Crea("Jova", 122);
        evento.Aggiungi("Jova", 22);
        evento.Chiudi("Jova");
    }

    public void Utente(Eventi evento){
        evento.Prenota("Jova", 10);
    }
}
