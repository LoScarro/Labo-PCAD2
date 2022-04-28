package eventi;

public class Admin implements Runnable {

    private final Eventi lista;
    private final String nomeEvento;
    private final int posti;

    public Admin(Eventi lista, String nome, int posti) {
        this.lista = lista;
        this.nomeEvento=nome;
        this.posti=posti;
    }
    
    @Override
    public synchronized void run() {
        try{

            lista.Crea(nomeEvento, posti);
            
            Thread.sleep(1);
            
            lista.Aggiungi(nomeEvento, posti);
            
            Thread.sleep(1);
            
            //lista.Chiudi("Jova");
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
