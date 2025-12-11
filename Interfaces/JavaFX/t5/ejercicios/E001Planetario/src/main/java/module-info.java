module com.example.e001planetario {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.e001planetario to javafx.fxml;
    exports com.example.e001planetario;
}