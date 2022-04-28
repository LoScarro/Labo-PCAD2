import eventi.Eventi;
import eventi.Admin;
import eventi.Utente;

import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;

public class Test {

    private volatile static Eventi lista = new Eventi();

    public static void main(String args[]) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));
        
        for (int i = 0; i < 1; i++) {
            try {
                pool.submit(new Admin(lista, "Jova", 122, 44));
                pool.submit(new Utente(lista, "Jova", 125));
                pool.submit(new Admin(lista, "Vasco", 143, 12));
                pool.submit(new Admin(lista, "Madonna", 463, 64));
                pool.submit(new Admin(lista, "Marley", 6457, 434));
                pool.submit(new Admin(lista, "Manson", 25, 4354));
                pool.submit(new Admin(lista, "ACDC", 547, 7654));
                pool.submit(new Utente(lista, "ACDC", 500));
                pool.submit(new Admin(lista, "Sabbath", 13, 75));
                pool.submit(new Utente(lista, "Sabbath", 77));
                pool.submit(new Admin(lista, "Roses", 865, 23));
                pool.submit(new Admin(lista, "Logic", 537, 864));
                pool.submit(new Admin(lista, "Eminem", 235, 753));
               
                pool.submit(new Utente(lista, "Eminem", 315));
                
                
                //pool.submit(new Utente(lista, "Roses", 1000));

                

            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }

        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }

        lista.ListaEventi();

    }
}
