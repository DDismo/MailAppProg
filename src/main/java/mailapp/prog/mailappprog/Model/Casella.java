package mailapp.prog.mailappprog.Model;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import java.util.LinkedList;

public class Casella {

    private final SimpleStringProperty userEmail;
    private final SimpleListProperty<Email> emailList;

    public Casella(){
        userEmail = new SimpleStringProperty("");
        emailList = new SimpleListProperty<>(FXCollections.observableList(new LinkedList<>()));
    }

    public SimpleStringProperty getUserEmailBean(){
        return userEmail;
    }

    public SimpleListProperty<Email> getEmailListProperty(){
        return emailList;
    }
}
