module org.example.t6_1_reproductor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens org.example.t6_1_reproductor to javafx.fxml;
    exports org.example.t6_1_reproductor;
}