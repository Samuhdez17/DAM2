module org.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    exports org.example.javafx.t1.ejercicios;
    exports org.example.javafx.t1.inicios;
    opens org.example.javafx.t1.inicios to javafx.fxml;
    opens org.example.javafx.t1.ejercicios to javafx.fxml;
    exports org.example.javafx.t2.ejercicios;
    opens org.example.javafx.t2.ejercicios to javafx.fxml;
    exports org.example.javafx.t2.inicios;
    opens org.example.javafx.t2.inicios to javafx.fxml;
}