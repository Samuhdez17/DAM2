module org.example.ensenanzas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ensenanzas to javafx.fxml;
    exports org.example.ensenanzas;
}