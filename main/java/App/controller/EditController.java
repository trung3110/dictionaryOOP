package App.controller;

import App.Processing.WordExplain;
import App.Processing.Words;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;


public class EditController  implements Initializable {
    @FXML
    TextField up_input, up_outEng, up_outVie, up_outType;
    @FXML
    TextArea up_outExtra;
    @FXML
    Text up_message;
    @FXML
    MenuButton menuType;
    @FXML
    Button btn_update, btn_up_ok;

    private Words data ;//Lấy từ database
    private Set<String> arrayEngWord = data.words.keySet();
    private AutoCompletionBinding<String> suggestWord;

    private String oldEng;
    private String oldType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        suggestWord = TextFields.bindAutoCompletion(up_input, arrayEngWord);
        suggestWord.setPrefWidth(up_input.getPrefWidth());

        btn_update.disableProperty().bind(
            up_outEng.textProperty().isEmpty()
                    .or(up_outVie.textProperty().isEmpty())
                    .or(up_outType.textProperty().isEmpty())
                    .or(up_outExtra.textProperty().isEmpty())
        );
        btn_up_ok.disableProperty().bind(
                up_input.textProperty().isEmpty()
        );
    }


    public void actionDirectAdd(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/add.fxml"));

        Parent addParent = loader.load();
        Scene addScene = new Scene(addParent);

        stage.setScene(addScene);
    }

    public void actionDirectBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/app.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
    }
}
