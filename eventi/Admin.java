package eventi;

public class Admin implements Runnable {

    private final Eventi lista;
    private final String nomeEvento;
    private final int postiDaAgg;

    public Admin(Eventi lista, String nome, int postiDaAgg) {
        this.lista = lista;
        this.nomeEvento = nome;
        this.postiDaAgg = postiDaAgg;
    }

    @Override
    public void run() {
        try {
            
            lista.Aggiungi(nomeEvento, postiDaAgg);

            Thread.sleep(100);
            
            //lista.Chiudi(nomeEvento);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
