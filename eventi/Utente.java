package eventi;

public class Utente implements Runnable {

    private final Eventi lista;
    private final String nome;
    private final int posti;

    public Utente(Eventi lista, String nome, int posti) {
        this.lista = lista;
        this.nome=nome;
        this.posti=posti;
    }

    @Override
    public void run() {
        try {
            lista.Prenota(nome, posti);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}