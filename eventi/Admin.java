package eventi;

public class Admin implements Runnable {

    private final Eventi lista;
    private final String nomeEvento;
    private final int postiIniziali;
    private final int postiDaAgg;

    public Admin(Eventi lista, String nome, int postiIniziali, int postiDaAgg) {
        this.lista = lista;
        this.nomeEvento = nome;
        this.postiIniziali = postiIniziali;
        this.postiDaAgg = postiDaAgg;
    }

    @Override
    public void run() {
        try {
            lista.Crea(nomeEvento, postiIniziali);

            Thread.sleep(1000);

            lista.Aggiungi(nomeEvento, postiDaAgg);

            Thread.sleep(1000);
            
            //lista.Chiudi(nomeEvento);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
