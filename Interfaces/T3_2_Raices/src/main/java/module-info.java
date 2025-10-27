module org.example.t3_2_raices {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.t3_2_raices to javafx.fxml;
    exports org.example.t3_2_raices;
}