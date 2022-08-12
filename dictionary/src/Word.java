import java.util.ArrayList;

public class Word {
   private String word_target;
   private String pronunciation;
   private ArrayList<VieMeanings> meanings;

   public Word() {
       this.word_target = word_target;
       this.pronunciation = pronunciation;
       meanings = new ArrayList<VieMeanings>();
   }

   public void setWord_target( String word_target){
       this.word_target = word_target;
   }
   public String getWord_target(){
       return word_target;
   }

    public void setMeanings(ArrayList<VieMeanings> meanings) {
        this.meanings = meanings;
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
       }
       return s;
    }

    public String toString() {
       String s = meanings.get(0).getExplain();
       return getWord_target() + "  " + s;
    }
}
