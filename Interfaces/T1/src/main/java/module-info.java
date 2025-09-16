module org.example.t1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.t1 to javafx.fxml;
    exports org.example.t1;
}