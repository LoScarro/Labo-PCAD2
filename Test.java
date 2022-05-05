import eventi.Eventi;
import eventi.Admin;
import eventi.Utente;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.*;
import java.io.IOException;

public class Test {

    private volatile static Eventi lista = new Eventi();

    public static List<String> inizialize(){
        List<String> datiEventi = new ArrayList<String>();
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"nomieventi"));
			String line = reader.readLine();
			while (line != null) {
                datiEventi.add(line);
				line = reader.readLine();
                datiEventi.add(line);
                line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return datiEventi;
    }

    public static void main(String args[]) throws Exception {
        List<String> datiEventi = inizialize();
        
        for (int i = 0; i < datiEventi.size(); i+=4){
            lista.Crea(datiEventi.get(i), Integer.parseInt(datiEventi.get(i+3)));       //Creo gli eventi
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("              Via alle prenotazioni!              ");
        System.out.println("--------------------------------------------------");
        
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 15, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));
            
        try {
            for (int i = 0; i < datiEventi.size(); i+=4){
                pool.submit(new Utente(lista, datiEventi.get(i), Integer.parseInt(datiEventi.get(i+1))));       //Prenoto i posti per l'evento
                pool.submit(new Admin(lista, datiEventi.get(i), Integer.parseInt(datiEventi.get(i+2))));        //aggiungo posti all'evento, aspetto e poi lo chiudo
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        System.out.println("--------------------------------------------------");
        System.out.println("              Prenotazioni concluse!              ");
        System.out.println("--------------------------------------------------");
        lista.ListaEventi();
        calculate();
    }

    public static void calculate() {

        String nome;
        int posti;

        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"assertion"));
			String line = reader.readLine();
			while (line != null) {
                nome = line;
                line = reader.readLine();
                posti = Integer.parseInt(line);
				assert lista.getPosti(nome) == posti;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
