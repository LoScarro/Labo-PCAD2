package eventi;

public class Evento {
    private String nome;
    private int postiLiberi;

    public Evento(String nome, int postiLiberi) {
        this.nome = nome;
        this.postiLiberi = postiLiberi;
    }

    public void AggiungiPosti(int posti) {
        this.postiLiberi += posti;
        //postiLiberi.notify();
    }

    public String getName() {
        return this.nome;
    }

    public int getPostiLiberi() {
        return this.postiLiberi;
    }

    public void Prenota(int posti) {
        this.postiLiberi -= posti;
    }
}

























