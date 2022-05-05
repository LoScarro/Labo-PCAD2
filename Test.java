import eventi.Eventi;
import eventi.Admin;
import eventi.Utente;

import java.io.BufferedReader;  
import java.io.FileReader;  
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

    public static void main(String args[]) throws Exception {
        
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
        
        /*String line;  
        
        FileReader file = new FileReader("nomieventi.txt");  
        
        BufferedReader br = new BufferedReader(file);
        

        while((line = br.readLine()) != null) {  
            //Splits each line into words  
            String words[] = line.split("");  
            //Counts each word  
            System.out.println(words[0]);
  
        }*/


        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 15, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 1; i++) {
            try {

                pool.submit(new Utente(lista, "Queen", 125));
                pool.submit(new Admin(lista, "Queen", 90));
                pool.submit(new Utente(lista, "ACDC", 500));
                pool.submit(new Admin(lista, "ACDC", 125));
                pool.submit(new Utente(lista, "Black Sabbath", 77));
                pool.submit(new Admin(lista, "Black Sabbath", 100));
                pool.submit(new Utente(lista, "U2", 315));
                pool.submit(new Admin(lista, "U2", 305));
                pool.submit(new Utente(lista, "Guns N' Roses", 100));
                pool.submit(new Admin(lista, "Guns N' Roses", 400));
                pool.submit(new Utente(lista, "Marilyn Manson", 134));
                pool.submit(new Admin(lista, "Marilyn Manson", 200));
                pool.submit(new Utente(lista, "Bob Marley", 4278));
                pool.submit(new Admin(lista, "Bob Marley", 100));
                pool.submit(new Utente(lista, "Aerosmith", 500));
                pool.submit(new Admin(lista, "Aerosmith", 100));
                pool.submit(new Utente(lista, "Bon Jovi", 123));
                pool.submit(new Admin(lista, "Bon Jovi", 10));
                pool.submit(new Utente(lista, "Madonna", 500));
                pool.submit(new Admin(lista, "Madonna", 100));
                pool.submit(new Utente(lista, "Beatles", 150));
                pool.submit(new Admin(lista, "Beatles", 100));

            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }

        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        System.out.println(lista.getPosti("Madonna"));
        lista.ListaEventi();

    }

    public static void calculate(String nome) {
        System.out.println(lista.getPosti(nome));
    }
}
