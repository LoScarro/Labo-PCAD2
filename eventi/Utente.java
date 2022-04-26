package eventi;

public class Utente implements Runnable {

    Eventi lista;

    public Utente(Eventi lista) {
        this.lista = lista;
    }

    public void run() {
        lista.Prenota("Jova", 10);
        lista.ListaEventi();
    }
}