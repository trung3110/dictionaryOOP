package Controll;

import java.util.ArrayList;

public class Word {
   private String word_target;
   private String word_explain;
   private String pronunciation;
   private ArrayList<VieMeanings> meanings;

   public Word() {
       this.word_explain = "";
       this.word_target = "";
       this.pronunciation = "";
       meanings = new ArrayList<VieMeanings>();
   }

   public Word(String word_target, String word_explain, String pronunciation, ArrayList<VieMeanings> meanings) {
       this.word_target = word_target;
       this.word_explain = word_explain;
       this.pronunciation = pronunciation;
       this.meanings = meanings;
   }

   public void setWord_target( String word_target){
       this.word_target = word_target;
   }
   public String getWord_target(){
       return word_target;
   }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public ArrayList<VieMeanings> getMeanings() {
        return meanings;
    }

    public void addMeans(VieMeanings s) {
       meanings.add(s);
    }

    public String fullWord() {
       String s = getWord_target();
       s += "\n" + "Cách phát âm: " + getPronunciation();

       for (int i = 0; i < meanings.size(); ++i) {
           s += "\n";
           s += meanings.get(i).toString();
           //System.out.println(s);
       }
       return s;
    }

    public String toString() {
       return getWord_target() + "  " + getWord_explain();
    }
}
