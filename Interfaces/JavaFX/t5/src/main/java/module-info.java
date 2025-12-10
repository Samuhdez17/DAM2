module org.example.t5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens enseñanzas to javafx.fxml;
    exports enseñanzas;

    opens ejercicios.E001SistemaSolar to javafx.fxml;
    exports ejercicios.E001SistemaSolar;
}