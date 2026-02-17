module org.example.examen2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.examen2 to javafx.fxml;
    exports org.example.examen2;
}