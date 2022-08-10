public class DictionaryCommandline {
    public static void showAllWords() {
        System.out.println("No \t |English \t |Vietnamese");

        int numWordInDic = Dictionary.WordList.size();

        for(int i = 0; i < numWordInDic; i++) {
            String engWord = Dictionary.WordList.get(i).getWord_target();
            String vieWord = Dictionary.WordList.get(i).getWord_explain();
            System.out.printf("%d\t |%s\t |%s\n", i + 1, engWord, vieWord);
        }
    }

    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        DictionaryCommandline.showAllWords();
    }
    public static void dictionaryAdvanced() {
        DictionaryManagement.insertfromFile();
        DictionaryCommandline.showAllWords();
        DictionaryManagement.dictionaryLookup();
    }

    public static void main(String[] args){
        //showAllWords();
        dictionaryAdvanced();
        DictionaryManagement.dictionaryChanges();
        showAllWords();
    }
}
