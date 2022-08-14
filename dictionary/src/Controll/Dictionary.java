package Controll;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    public static List<Word> WordList;

    static {
        WordList = new ArrayList<Word>();
    }

    public static void addWord(Word wrd) {
        WordList.add(wrd);
    }
}
