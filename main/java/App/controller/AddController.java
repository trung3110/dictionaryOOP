package App.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController  implements Initializable {
    @FXML
    TextField add_eng, add_vie, add_type, add_pathAudio;
    @FXML
    TextArea add_extra;
    @FXML
    Button btn_add;
    @FXML
    Text add_message;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_add.disableProperty().bind(
                add_eng.textProperty().isEmpty()
                        .or(add_vie.textProperty().isEmpty())
                        .or(add_type.textProperty().isEmpty())

        );
    }


    public void directEdit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/edit.fxml"));

        Parent editParent = loader.load();
        Scene editScene = new Scene(editParent, 900, 600);

        stage.setScene(editScene);
    }
}
