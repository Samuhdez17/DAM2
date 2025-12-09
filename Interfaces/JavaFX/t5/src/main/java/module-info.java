module org.example.t5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens enseñanzas to javafx.fxml;
    exports enseñanzas;

//    opens ejercicios to javafx.fxml;
//    exports ejercicios;
}