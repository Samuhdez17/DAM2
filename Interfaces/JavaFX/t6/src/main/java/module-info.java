module org.example.t6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens org.example.t6.ensenanzas to javafx.fxml;
    exports org.example.t6.ensenanzas;

    exports org.example.t6.ejercicios.T6_1_Reproductor;
    opens org.example.t6.ejercicios.T6_1_Reproductor to javafx.fxml;
}