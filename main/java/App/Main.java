package App;

import App.Processing.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.InputStream;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/app.fxml"));
        Scene scene = new Scene(root, 1200, 800);

        InputStream input = this.getClass().getResourceAsStream("/View/image/LogoApp.png");
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
