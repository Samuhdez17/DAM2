module org.example.t4_e003cambioescena {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.t4_e003cambioescena to javafx.fxml;
    exports org.example.t4_e003cambioescena;
}