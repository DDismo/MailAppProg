package mailapp.prog.mailappprog.Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Email implements Serializable {

    @Serial
    private final static long serialVersionUID = 6529685098267757690L;
    private static int id;
    private String mittente;
    private final List<String> destinatari;
    private String titolo;
    private String messaggio;

    public Email(){
        id++;
        destinatari = new LinkedList<>();
    }

    public String getMittente() {
        return mittente;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    public List<String> getDestinatari() {
        return destinatari;
    }

    public void addDestinatario(String dest){
        destinatari.add(dest);
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

}
