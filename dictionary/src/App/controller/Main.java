package App.controller;

import Controll.*;
import Controll.Dictionary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;

public class Main extends Application {

    public static final String url = "/resources/View";

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL x = Objects.requireNonNull(getClass().getResource(url + "/app.fxml"));
        Parent root = FXMLLoader.load(x);
        Scene scene = new Scene(root, 1200, 800);

        InputStream input = this.getClass().getResourceAsStream( url + "/image/LogoApp.png");
        Image image = new Image(input);
        primaryStage.getIcons().add(image);

        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        Dictionary.WordList = ConnectToSQL.importDatabase();
        launch(args);
    }
}
