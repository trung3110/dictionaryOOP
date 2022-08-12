import java.util.ArrayList;

public class VieMeanings {
    private String wordType;
    private String explain;
    private ArrayList<String> Example;

    public VieMeanings() {
        this.wordType = "";
        this.explain = "";
        this.Example = new ArrayList<String>();
    }

    public VieMeanings(String wordType, String explain, ArrayList<String> ExCase) {
        this.wordType = wordType;
        this.explain = explain;
        this.Example = ExCase;
    }
    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public ArrayList<String> getExample() {
        return Example;
    }

    public void addExample(String s) {
        this.Example.add(s);
    }

    public String toString() {
        String s = "Loại từ : " + wordType + "\n" + "Giải thích : " + explain + "\n" + "Ví dụ :\n\t";
        for (int i = 0; i < Example.size(); ++i) {
            s += Example.get(i);
            s += "\n\t";
        }
        return s;
    }
}