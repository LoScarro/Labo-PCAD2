import eventi.Eventi;

public class Test {
    public static void main(String args[]){
        Eventi lista = new Eventi();
        lista.Crea("Jova", 122);
        lista.ListaEventi();
        lista.Aggiungi("Jova", 22);
        lista.ListaEventi();
        lista.Chiudi("Jova");
        lista.ListaEventi();
        lista.Crea("Jova", 1222);
        lista.ListaEventi();
        lista.Crea("Jova", 12);
    }
}
