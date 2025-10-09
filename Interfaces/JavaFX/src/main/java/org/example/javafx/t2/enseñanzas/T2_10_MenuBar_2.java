package org.example.javafx.t2.enseñanzas;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class T2_10_MenuBar_2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MenuBar menuBar = new MenuBar();
        Menu m1 = new Menu("menu 1");
        Menu m2 = new Menu("menu 2");
        Menu m3 = new Menu("menu 3");
        Menu m4 = new Menu("menu 4");
        menuBar.getMenus().addAll(m1, m2, m3, m4);

        MenuItem menuItem1_1 = new MenuItem("menu item 1 del menu 1");
        MenuItem menuItem1_2 = new MenuItem("menu item 2 del menu 1");
        MenuItem menuItem1_3 = new MenuItem("menu item 3 del menu 1");

        Menu menu1_4 = new Menu("menu 4 del menu 1");
        MenuItem menuItem1_4_1 = new MenuItem("menu item 1 del menu 4");
        MenuItem menuItem1_4_2 = new MenuItem("menu item 2 del menu 4");
        MenuItem menuItem1_4_3 = new MenuItem("menu item 3 del menu 4");
        MenuItem menuItem1_4_4 = new MenuItem("menu item 4 del menu 4");
        menu1_4.getItems().addAll(menuItem1_4_1, menuItem1_4_2, menuItem1_4_3, menuItem1_4_4);

        m1.getItems().addAll(menuItem1_1, menuItem1_2, menuItem1_3, menu1_4);

        MenuItem menuItem2_1 = new MenuItem("menu item 1 del menu 2");
        MenuItem menuItem2_2 = new MenuItem("menu item 2 del menu 2");
        MenuItem menuItem2_3 = new MenuItem("menu item 3 del menu 2");
        m2.getItems().addAll(menuItem2_1, menuItem2_2, menuItem2_3);

        MenuItem menuItem3_1 = new MenuItem("menu item 1 del menu 3");
        MenuItem menuItem3_2 = new MenuItem("menu item 2 del menu 3");
        MenuItem menuItem3_3 = new MenuItem("menu item 3 del menu 3");
        m3.getItems().addAll(menuItem3_1, menuItem3_2, menuItem3_3);

        MenuItem menuItem4_1 = new MenuItem("menu item 1 del menu 4");
        MenuItem menuItem4_2 = new MenuItem("menu item 2 del menu 4");
        MenuItem menuItem4_3 = new MenuItem("menu item 3 del menu 4");
        m4.getItems().addAll(menuItem4_1, menuItem4_2, menuItem4_3);

        menuItem1_2.setDisable(true);
        SeparatorMenuItem separador1 = new SeparatorMenuItem();
        SeparatorMenuItem separador2 = new SeparatorMenuItem();

        m1.getItems().add(0, separador1);
        m1.getItems().add(2, separador2);

        menuItem1_4_2.setAccelerator(KeyCombination.keyCombination("Ctrl+K"));

        Image imagen = new Image("file:src/main/java/org/example/javafx/t2/enseñanzas/descarga.png");
        ImageView imageView = new ImageView(imagen);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        menuItem1_4_4.setGraphic(imageView);

        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);

        stage.setScene(new javafx.scene.Scene(bp));
        stage.setTitle("T2_10_MenuBar_1");
        stage.show();
    }
}
