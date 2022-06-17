module mailapp.prog.mailappprog {
    requires javafx.controls;
    requires javafx.fxml;


    opens mailapp.prog.mailappprog to javafx.fxml;
    exports mailapp.prog.mailappprog;
}