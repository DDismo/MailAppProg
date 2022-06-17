package mailapp.prog.mailappprog.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mailapp.prog.mailappprog.Controller.ServerController;

import java.io.IOException;

public class ServerGUI extends Application {
    private ServerController sc;

    public static void main(String[] args) {
        launch(args);
    }

    public void stop(){
        try {
            sc.stopServer();
        }catch(Exception e){
            sc.printError(e);
            try {
                Thread.sleep(3000);
                sc.stopServer();
            }catch(Exception ex){
                sc.printError(ex.getMessage()+"; interruzione forzata...");
                sc.interrupt();
            }
        }finally{
            //salva il salvabile
            sc.saveState();
        }
    }

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(ServerGUI.class.getResource("ServerApplication.fxml"));
        Parent root = loader.load();
        stage.setTitle("ServerMail");
        stage.setScene(new Scene(root));
        sc = loader.getController();
        try{
            sc.initializeServer();
            stage.show();
        }catch(IOException e){
            System.err.println("Errore durante la generazione del server: "+ e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
