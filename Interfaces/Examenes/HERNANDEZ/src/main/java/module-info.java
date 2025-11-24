module org.example.hernandez {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hernandez to javafx.fxml;
    exports org.example.hernandez;
}