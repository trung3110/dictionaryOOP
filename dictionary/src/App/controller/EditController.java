package App.controller;

import Controll.*;
import Controll.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class EditController implements Initializable {
  @FXML TextField up_input, up_outType;
  @FXML Label wordTargetLabel;
  @FXML TextArea up_outExtra;
  @FXML Text up_message;

  @FXML ChoiceBox<String> choiceType;
  @FXML Button btn_update, btn_up_ok;

  private Word wrd;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    up_input.setOnKeyPressed(
        e -> {
          if (e.getCode().equals(KeyCode.ENTER)) {
            try {
              export();
            } catch (SQLException exception) {
              exception.printStackTrace();
            }
          }
        });

    choiceType.setOnAction(
        e -> {
          VieMeanings vieMeanings =
              wrd.getVieMeanings(choiceType.getSelectionModel().getSelectedItem());
          up_outType.setText(vieMeanings.getWordType());
          up_outExtra.setText(vieMeanings.getInfoExplain());
        });

    btn_update
        .disableProperty()
        .bind(up_outType.textProperty().isEmpty().or(up_outExtra.textProperty().isEmpty()));
  }

  public void actionDirectAdd(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(Main.url + "/add.fxml"));

    Parent addParent = loader.load();
    Scene addScene = new Scene(addParent);

    stage.setScene(addScene);
  }

  public void actionDirectBack(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(Main.url + "/app.fxml"));

    Parent root = loader.load();
    Scene scene = new Scene(root);

    stage.setScene(scene);
  }

  @FXML
  public void update() throws SQLException {
    List<String> list = Arrays.asList(up_outExtra.getText().split("\n"));
    VieMeanings vieMeanings = new VieMeanings(up_outType.getText(), list);
    wrd.addMeans(vieMeanings);
    choiceType.getItems().clear();
    choiceType.getItems().addAll(wrd.getListWordType());
    ConnectToSQL.EditDatabase(wrd.fullWord(), wrd.getWord_target());
  }

  @FXML
  public void export() throws SQLException {
    if (DictionaryManagement.dictionarySearchers(up_input.getText()) == -1) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("");
      alert.setHeaderText(null);
      alert.setContentText("Bạn có muốn thêm từ '" + up_input.getText() + "' vào từ điển không?");

      if (alert.showAndWait().get() == ButtonType.CANCEL) {
        return;
      }
    }

    up_outType.setDisable(false);
    up_outExtra.setDisable(false);
    choiceType.setDisable(false);

    wordTargetLabel.setText(up_input.getText());
    if (DictionaryManagement.dictionarySearchers(up_input.getText()) == -1) {
      Dictionary.addWord(new Word(up_input.getText()));
      ConnectToSQL.InsertDatabase(new Word(up_input.getText()));
    }

    wrd = DictionaryManagement.dictionarySearcherWord(up_input.getText());

    choiceType.getItems().clear();
    choiceType.getItems().addAll(wrd.getListWordType());
  }
}
