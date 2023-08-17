package Controll;

import java.sql.SQLException;
import java.util.ArrayList;

public class DictionaryCommandline {
    public static void showAllWords() {
        System.out.println("No \t |English \t |Vietnamese");

        int numWordInDic = Dictionary.WordList.size();

        for(int i = 0; i < numWordInDic; i++) {
            String engWord = Dictionary.WordList.get(i).getWord_target();
            String vieWord = Dictionary.WordList.get(i).getWord_explain();
            System.out.printf("%d\t |%s\t\t |%s\n", i + 1, engWord, vieWord);
        }
    }

    public static void dictionaryAdvanced() {
        DictionaryManagement.insertFromCommandline();
        DictionaryCommandline.showAllWords();
        DictionaryManagement.dictionaryDelete();
        DictionaryManagement.dictionaryChanges();
        showAllWords();

    }

    public static void main(String[] arg) throws SQLException {
        //dictionaryAdvanced();

        Dictionary.WordList = ConnectToSQL.importDatabase();


    }
}
