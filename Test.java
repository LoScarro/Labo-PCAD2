import eventi.Eventi;
import eventi.Admin;

import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;

public class Test {

    private volatile static Eventi lista = new Eventi();

    public static void main(String args[]) {


        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 2; i++) {
            try{
                
                pool.submit(new Admin(lista, "Jova", 122));
            
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
            
            lista.ListaEventi();
        }
        
        pool.shutdown();
        while (!pool.isTerminated()) {}
        System.out.println("Finished all threads");

        /*      DEBUG
        lista.Crea("Jova", 122);
        lista.ListaEventi();
        lista.Aggiungi("Jova", 22);
        lista.ListaEventi();
        lista.Chiudi("Jova");
        lista.ListaEventi();
        lista.Crea("Jova", 1222);
        lista.ListaEventi();
        lista.Prenota("Jova", 10);
        lista.ListaEventi();
        */

    }
}
