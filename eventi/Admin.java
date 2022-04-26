package eventi;

public class Admin implements Runnable {

    private final Eventi lista;

    public Admin(Eventi lista) {
        this.lista = lista;
    }
    
    @Override
    public synchronized void run() {
        try{

            lista.Crea("Jova", 122);
            
            Thread.sleep(1);
            
            lista.Aggiungi("Jova", 22);
            
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
