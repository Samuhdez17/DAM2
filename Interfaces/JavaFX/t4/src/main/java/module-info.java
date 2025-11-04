module org.example.t4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.t4 to javafx.fxml;
    exports org.example.t4;
}