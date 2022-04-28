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

        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 1; i++) {
            try {
                pool.submit(new Utente(lista, "Queen", 125));
                pool.submit(new Admin(lista, "Queen", 122, 44));
                pool.submit(new Admin(lista, "Beatles", 143, 12));
                pool.submit(new Admin(lista, "Madonna", 463, 64));
                pool.submit(new Admin(lista, "Bob Marley", 6457, 434));
                pool.submit(new Admin(lista, "Marilyn Manson", 25, 4354));
                pool.submit(new Admin(lista, "ACDC", 547, 7654));
                pool.submit(new Utente(lista, "Bon Jovi", 500));
                pool.submit(new Utente(lista, "ACDC", 500));
                pool.submit(new Admin(lista, "Black Sabbath", 13, 75));
                pool.submit(new Utente(lista, "Black Sabbath", 77));
                pool.submit(new Admin(lista, "Guns N' Roses", 865, 23));
                pool.submit(new Admin(lista, "Aerosmith", 537, 864));
                pool.submit(new Admin(lista, "U2", 235, 753));
                pool.submit(new Utente(lista, "U2", 315));
                pool.submit(new Utente(lista, "Guns N' Roses", 100));
                pool.submit(new Admin(lista, "Bon Jovi", 122, 700));

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
