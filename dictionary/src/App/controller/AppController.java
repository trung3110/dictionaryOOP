package App.controller;

import Controll.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import Controll.DictionaryManagement;

public class AppController {
    @FXML
    private TextField inputWord;
    @FXML
    private Label vieWord;
    @FXML
    private Label description;
    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> detail;

    private ObservableList<String> similarWord;
    private ObservableList<String> similarWord1;
    private String pathAudio = null;

    public AppController() {
    }


    //nhập từ tìm kiếm và hiển thị danh sách từ gợi ý trùng với input
    public void readKeyPressed(KeyEvent key) throws SQLException {
        if(key.getCode() == KeyCode.ENTER) {
            String input = inputWord.getText();
        }
        else if(!inputWord.getText().equals("")){

            similarWord =
                    FXCollections.observableArrayList( DictionaryManagement.dictionarySearcher(inputWord.getText()) );

            listView.setItems(similarWord);
            listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);// just allows to choose a single line
        }
    }

    //chọn từ trong danh sách gợi ý
    public void getFieldChoosed() throws SQLException {
        String valField = listView.getSelectionModel().getSelectedItem();
        Word wrd = new Word();
        if (valField != null) {
            inputWord.setText(valField);
            wrd = DictionaryManagement.dictionarySearchers(valField);
        }

        //description.setText(wrd.fullWord());
        similarWord1 =
                FXCollections.observableArrayList( wrd.getMeanings().toString() );
        detail.setItems(similarWord1);
        //detail.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        vieWord.setText(wrd.getPronunciation());
    }

    //chuyển scence
    public void showEditScene(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Main.url + "/edit.fxml"));

        Parent editParent = loader.load();
        Scene editScene = new Scene(editParent, 900, 600);

        stage.setScene(editScene);
    }

    //chuyển scence
    public void showDeleteScene(MouseEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Main.url + "/delete.fxml"));

        Parent editParent = loader.load();
        Scene editScene = new Scene(editParent);

        stage.setScene(editScene);
    }

    // mở file phát âm
    public void playAudio() {
        if(pathAudio != null && !pathAudio.isEmpty()) {
            try {
                Media audio = new Media(this.getClass().getResource("/resources/data/audio/" + pathAudio).toString());
                MediaPlayer mediaPlayer = new MediaPlayer(audio);
                mediaPlayer.play();
            }
            catch (Exception e) {
                System.out.println("Lỗi !!! không tạo được Media audio");
            }
        }
        else {
            System.out.println("Thiếu file phát âm");
        }
    }

}

