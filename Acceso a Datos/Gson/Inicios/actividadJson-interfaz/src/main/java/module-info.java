module org.example.actividadjsoninterfaz {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.actividadjsoninterfaz to javafx.fxml;
    exports org.example.actividadjsoninterfaz;
}