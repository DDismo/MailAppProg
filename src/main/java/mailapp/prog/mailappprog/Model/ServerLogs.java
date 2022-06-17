package mailapp.prog.mailappprog.Model;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ServerLogs {
    SimpleStringProperty log;
    List<String> logs;

    public ServerLogs(){
        log = new SimpleStringProperty("");
        logs = new LinkedList<>();
    }


    public SimpleStringProperty getLogsProperty() {
        return log;
    }

    public void addLog(String text){
        text = "["+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))+"] "+text+"\n";
        logs.add(text);
        log.set(text);
    }

    public List<String> getLogs(){
        return logs;
    }
}
