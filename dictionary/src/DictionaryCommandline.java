import java.sql.SQLException;
import java.util.ArrayList;

public class DictionaryCommandline {
    public static void showAllWords() {
        System.out.println("No \t |English \t |Vietnamese");

        int numWordInDic = Dictionary.WordList.size();

        for(int i = 0; i < numWordInDic; i++) {
            String engWord = Dictionary.WordList.get(i).getWord_target();
            String vieWord = Dictionary.WordList.get(i).getMeanings().get(0).getExplain();
            System.out.printf("%d\t |%s\t\t |%s\n", i + 1, engWord, vieWord);
        }
    }

    public static void dictionaryAdvanced() {
        DictionaryManagement.insertfromFile();
        DictionaryCommandline.showAllWords();
        DictionaryManagement.dictionaryDelete();
        DictionaryManagement.dictionaryChanges();
        showAllWords();
    }

    public static void main(String[] arg) {
        dictionaryAdvanced();
    }
}
