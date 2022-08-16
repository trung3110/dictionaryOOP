package Controll;

import java.util.ArrayList;
import java.util.List;

public class VieMeanings {
    private String wordType;

    private List<String> listExplain;

    public VieMeanings() {
        this.wordType = "";
        listExplain = new ArrayList<String>();
    }

    public VieMeanings(String wordType) {
        this.wordType = wordType;
    }

    public VieMeanings(String wordType, List<String> listExplain) {
        this.wordType = wordType;
        this.listExplain = listExplain;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public List<String> getListExplain() {
        return listExplain;
    }

    public void addExplain(String ex) {
        listExplain.add(ex);
        //System.out.println(listExplain.get(0));
    }

    public String getInfoExplain() {
        String s = "";
        for (int i = 0; i < listExplain.size(); ++i) {
            if ( s != "" ) {
                s += "\n";
            }
            s += listExplain.get(i);
        }

        return s;
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