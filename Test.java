import eventi.Eventi;
import eventi.Admin;
import eventi.Utente;

import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.HashMap;
/*import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.IOException;*/

public class Test {

    private volatile static Eventi lista = new Eventi();
    private static HashMap<String, Integer> noThread = new HashMap<String, Integer>();

    public static void main(String args[]) {
        
        lista.Crea("Queen", 122);
        lista.Crea("Beatles", 143);
        lista.Crea("Madonna", 463);
        lista.Crea("Bob Marley", 6457);
        lista.Crea("Marilyn Manson", 25);
        lista.Crea("ACDC", 547);
        lista.Crea("Bon Jovi", 122);
        lista.Crea("Black Sabbath", 13);
        lista.Crea("Aerosmith", 537);
        lista.Crea("U2", 235);
        lista.Crea("Guns N' Roses", 865);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 1; i++) {
            try {

                pool.submit(new Utente(lista, "Queen", 125));
                pool.submit(new Admin(lista, "Queen", 90));
                pool.submit(new Utente(lista, "ACDC", 500));
                pool.submit(new Admin(lista, "ACDC", 125));
                pool.submit(new Utente(lista, "Black Sabbath", 77));
                pool.submit(new Utente(lista, "Black Sabbath", 77));
                pool.submit(new Utente(lista, "U2", 315));
                pool.submit(new Utente(lista, "Guns N' Roses", 100));
                pool.submit(new Utente(lista, "Marilyn Manson", 134));
                pool.submit(new Utente(lista, "Bob Marley", 4278));
                pool.submit(new Utente(lista, "Aerosmith", 500));
                pool.submit(new Utente(lista, "Bon Jovi", 123));
                pool.submit(new Utente(lista, "Madonna", 400));
                pool.submit(new Utente(lista, "Beatles", 150));

            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }

        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }

        lista.ListaEventi();

    }

    public static void calculate(String nome, int posti) {
        noThread.putIfAbsent(nome, posti);
        int num = noThread.get(nome);
        noThread.put(nome, posti + num);
    }
}
