package App.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    public static final String url = "\\main\\resources\\View";

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            URL x = Objects.requireNonNull(getClass().getResource("dictionary/src/resources/View/ccc.fxml"));
        } catch (NullPointerException e) {
            System.out.println("vcl");
            return;
        }


        /*Parent root = FXMLLoader.load(x);
        Scene scene = new Scene(root, 1200, 800);

        InputStream input = this.getClass().getResourceAsStream( "/image/LogoApp.png");
        Image image = new Image(input);
        primaryStage.getIcons().add(image);

        primaryStage.setTitle("Dictionary Application");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
