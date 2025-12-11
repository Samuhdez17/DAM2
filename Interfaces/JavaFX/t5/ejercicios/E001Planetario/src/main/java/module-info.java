module com.example.e001planetario {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens com.example.e001planetario to javafx.fxml;
    exports com.example.e001planetario;
}