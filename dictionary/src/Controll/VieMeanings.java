package Controll;

import java.util.ArrayList;

public class VieMeanings {
    private String wordType;

    private ArrayList<String> listExplain;

    public VieMeanings() {
        listExplain = new ArrayList<String>();
    }

    public VieMeanings(String wordType) {
        this.wordType = wordType;
    }

    public VieMeanings(String wordType, ArrayList<String> listExplain) {
        this.wordType = wordType;
        this.listExplain = listExplain;
    }
    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public void addExplain(String ex) {
        listExplain.add(ex);
    }

    public String toString() {
        String s = "*Loại từ : " + this.wordType;
        for (int i = 0; i < listExplain.size(); ++i) {
            s += "\n";
            s += listExplain.get(i);
        }

        return s;
    }
}