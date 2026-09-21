module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.view.cliente to javafx.fxml;
    exports com.example.view.cliente;

    opens com.example.view.servidor to javafx.fxml;
    exports com.example.view.servidor;
}
