package App.controller;

import Controll.ggAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TranslateController implements Initializable {
  @FXML TextArea langFrom, langTo;

  @FXML Button translateButton;

  @FXML Label langFromLabel, langToLabel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    translateButton.disableProperty().bind(langFrom.textProperty().isEmpty());
  }

  @FXML
  public void translate() {
    try {
      if (langFromLabel.getText().equals("English")) {
        langTo.setText(ggAPI.googleTranslate("en", "vi", langFrom.getText()));
      } else {
        langTo.setText(ggAPI.googleTranslate("vi", "en", langFrom.getText()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void swapAction() {
    String s = langFromLabel.getText();
    langFromLabel.setText(langToLabel.getText());
    langToLabel.setText(s);
  }

  public void actionDirectBack(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(Main.url + "/app.fxml"));

    Parent root = loader.load();
    Scene scene = new Scene(root);

    stage.setScene(scene);
  }
}
