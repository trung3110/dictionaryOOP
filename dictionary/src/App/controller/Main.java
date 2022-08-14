package App.controller;

import Controll.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    public static final String url = "/main/resources/View";

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url + "/app.fxml"));
        //FXMLLoader.load()
        Scene scene = new Scene(root, 1200, 800);

        InputStream input = this.getClass().getResourceAsStream(url + "/image/LogoApp.png");
        Image image = new Image(input);
        primaryStage.getIcons().add(image);

        primaryStage.setTitle("Dictionary Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
