package eventi;

public class Admin implements Runnable {

    Eventi lista;

    public Admin(Eventi lista) {
        this.lista = lista;
    }

    public void run() {
        lista.Crea("Jova", 122);
        try {
            Thread.sleep(400);
        } catch (Exception e) {
            System.out.println(e);
        }
        lista.Aggiungi("Jova", 22);
        try {
            Thread.sleep(400);
        } catch (Exception e) {
            System.out.println(e);
        }
        lista.Chiudi("Jova");
    }
}
