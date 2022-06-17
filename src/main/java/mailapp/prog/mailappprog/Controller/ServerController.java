package mailapp.prog.mailappprog.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import mailapp.prog.mailappprog.Model.Persistence;
import mailapp.prog.mailappprog.Model.Server;
import mailapp.prog.mailappprog.Model.ServerLogs;
import mailapp.prog.mailappprog.View.ServerGUI;

import java.io.IOException;
import java.util.Arrays;

public class ServerController {

    private Persistence p;
    private Server server;
    @FXML
    private TextArea serverLogs;

    @FXML
    public void startServerInstance(){
        System.out.println("[+] Server status: "+server.getStatus());
    }

    public void saveState(){
        p.saveState();
    }

    public void initializeServer() throws IOException{
        server = new Server(p);
    }

    public void stopServer() throws InterruptedException,IOException{
        server.closeConnection();
    }

    public void interrupt(){
        server.interrupt();
    }

    public <T> void printError(T elem){
        p.printErrorLog(elem);
    }


    public void initialize() {
        ServerLogs l = new ServerLogs();
        p = new Persistence(l);
        l.getLogsProperty().addListener(
                (ObservableValue<? extends String> observableValue, String oldString, String newString)->serverLogs.appendText(newString)
        );
    }
}
