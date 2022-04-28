package eventi;

public class Evento {
    private String nome;
    private Integer postiLiberi;

    public Evento(String nome, int postiLiberi) {
        this.nome = nome;
        this.postiLiberi = postiLiberi;
    }

    public void AggiungiPosti(int posti) {
        this.postiLiberi += posti;
        postiLiberi.notify();
    }

    public String getName() {
        return this.nome;
    }

    public int getPostiLiberi() {
        return this.postiLiberi;
    }

    public void chiudi(){
        postiLiberi.notify();
    }

    public synchronized void Prenota(int posti) {
        boolean isItPrinted = false;        //per stampare una volta sola il messaggio di waiting;
        while (postiLiberi - posti < 0) {
            try {
                postiLiberi.wait();
            } catch (Exception e) {
                if (isItPrinted == false) {
                    System.out.println("Sto aspettando che vengano aggiunti dei posti all'evento " + this.nome);
                    isItPrinted = true;
                }
            }
            
        }
        postiLiberi -= posti;
    }
}

























