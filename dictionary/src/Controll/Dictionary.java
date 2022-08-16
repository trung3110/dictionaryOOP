package Controll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dictionary {
    public static List<Word> WordList;

    static {
        WordList = new ArrayList<Word>();
    }

    public static void addWord(Word wrd) {
        WordList.add(wrd);

        Collections.sort(WordList, new Comparator<Word>() {
            @Override
            public int compare(Word wrd1, Word wrd2) {
                return wrd1.getWord_target().compareTo(wrd2.getWord_target());
            }
        });

    }


}
