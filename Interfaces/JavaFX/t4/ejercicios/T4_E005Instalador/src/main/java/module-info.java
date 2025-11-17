module org.example.t4_e005instalador {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.t4_e005instalador to javafx.fxml;
    exports org.example.t4_e005instalador;
}