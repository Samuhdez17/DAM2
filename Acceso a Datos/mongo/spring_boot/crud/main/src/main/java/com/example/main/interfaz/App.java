package com.example.main.interfaz;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class App extends Application {
    private static ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(App.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaz.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Lista de amigos");
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch();
    }
}