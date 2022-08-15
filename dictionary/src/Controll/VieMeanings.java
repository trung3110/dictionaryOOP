package Controll;

import java.util.ArrayList;

public class VieMeanings {
    private String wordType;

    private ArrayList<String> listExplain;

    public VieMeanings() {
        this.wordType = "";
        listExplain = new ArrayList<String>();
    }

    public VieMeanings(String wordType) {
        this.wordType = wordType;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public ArrayList<String> getListExplain() {
        return listExplain;
    }

    public void addExplain(String ex) {
        listExplain.add(ex);
        //System.out.println(listExplain.get(0));
    }
    public String toString() {
        String s = "\n";
        if ( this.wordType != null ) {
            s = "\n" + "LOẠI TỪ : " + this.wordType;
        }
        //System.out.println( getListExplain().size());
        for (int i = 0; i < listExplain.size(); ++i) {
            if ( s != "" ) {
                s += "\n";
            }
            s += listExplain.get(i);
            //System.out.println(listExplain.get(i));
        }

        return s;
    }
}