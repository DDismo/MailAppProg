package mailapp.prog.mailappprog.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Persistence {
    // deve tenere traccia delle caselle e salvare il suo stato una volta che ha concluso
    private final List<Casella> caselle;
    private final ServerLogs l;

    public Persistence(ServerLogs logs){
        caselle = new ArrayList<>();
        l = logs;
    }

    public <T> void printErrorLog(T elem){
        if(Exception.class.equals(elem.getClass())){
            Exception e = (Exception)elem;
            l.addLog("Errore interno: "+e.getMessage());
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }else if(String.class.equals(elem.getClass())){
            String text = (String)elem;
            l.addLog("Errore interno: "+text);
            System.err.println(text);
            System.err.println(Arrays.toString(Thread.currentThread().getStackTrace()));
        }
    }

    public void addLog(String text){
        l.addLog(text);
    }

    public void saveState(){
        //devo salvare i log
        //devo salvare lo stato corrente
    }
}
