package App.controller;


import App.Processing.Words;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController  implements Initializable{
    @FXML
    Text del_message;
    @FXML
    Button btn_delete;
    @FXML
    TextField del_input, del_inputType;

    private Words data ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_delete.disableProperty().bind(
                del_input.textProperty().isEmpty()
        );
    }

    //chuyển scence
    public void actionDirectBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/app.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    //xóa từ trong Database
    public void deleteWord() {
        del_message.setText("");
        if(!btn_delete.isDisable()) {
            String del_queryWord = del_input.getText();
            String del_queryType = del_inputType.getText();

            if(data.words.containsKey(del_queryWord)) {
                if(!del_queryType.equals("")) {
                    if(data.words.get(del_queryWord).extraWord.containsKey(del_queryType)) {
                      //  data.words.get(del_queryWord).extraWord.remove(del_queryType);
                       // deleteData.deleteType(del_queryWord, del_queryType);
                        del_message.setText("Xóa 1 loại của từ thành công !!!");
                    }
                    else del_message.setText("Không tồn tại loại của từ cần xóa !!!");
                }
                else {
                   // data.words.remove(del_queryWord);
                   // deleteData.deleteWord(del_queryWord);
                    del_message.setText("Xóa từ thành công !!!");
                }

            }
            else del_message.setText("Từ bạn xóa không tồn tại !!!");

        }

    }

}
