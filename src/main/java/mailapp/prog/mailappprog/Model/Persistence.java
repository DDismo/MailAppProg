package mailapp.prog.mailappprog.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Persistence {
    // deve tenere traccia delle caselle e salvare il suo stato una volta che ha concluso
    private final List<Casella> caselle;
    private final ServerLogs l;
    private final String fs = FileSystems.getDefault().getSeparator();

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

    private void saveLogs(){
        String dir = "src.main.java.mailapp.prog.mailappprog".replace(".", fs);
        File f = new File(dir, "server.log");
        try {
            f.createNewFile();
            try (FileWriter writer = new FileWriter(f, true)) {
                for (String log : l.getLogs()) {
                    writer.write(log);
                    writer.flush();
                }
            } catch (IOException e) {
                printErrorLog("Non sono riuscito a salvare i log, stream error");
            }
        }catch (IOException e) {
            printErrorLog(e);
        }
    }

    public void saveState(){
        //devo salvare i log
        saveLogs();
        //devo salvare lo stato corrente
    }
}
