module com.puj.patrones.caso_estudio_poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    requires gson;
    requires java.sql;
    exports poo.view;
    opens poo.view to javafx.fxml;
    exports poo.controller;
    exports poo.model;
    opens poo.controller to javafx.fxml;

}

