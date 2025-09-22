module org.example.t1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;


    opens org.example.t1 to javafx.fxml;
    exports org.example.t1;
}