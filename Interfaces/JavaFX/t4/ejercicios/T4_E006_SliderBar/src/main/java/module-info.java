module org.example.t4_e006_sliderbar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.t4_e006_sliderbar to javafx.fxml;
    exports org.example.t4_e006_sliderbar;
}