module org.example.t1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;


    exports org.example.t1.ejercicios;
    exports org.example.t1.inicios;
    opens org.example.t1.inicios to javafx.fxml;
    opens org.example.t1.ejercicios to javafx.fxml;
}