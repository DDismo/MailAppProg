module mailapp.prog.mailappprog {
    requires javafx.controls;
    requires javafx.fxml;


    opens mailapp.prog.mailappprog.View to javafx.fxml;
    exports mailapp.prog.mailappprog.View;

    opens mailapp.prog.mailappprog.Controller to javafx.fxml;
    exports mailapp.prog.mailappprog.Controller;
}