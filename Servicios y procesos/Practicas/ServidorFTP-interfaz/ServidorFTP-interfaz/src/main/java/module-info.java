module org.example.servidorftpinterfaz {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.servidorftpinterfaz to javafx.fxml;
    exports org.example.servidorftpinterfaz;
}