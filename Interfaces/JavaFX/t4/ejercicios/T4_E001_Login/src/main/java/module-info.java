module org.example.t4_e001_login {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.t4_e001_login to javafx.fxml;
    exports org.example.t4_e001_login;
}