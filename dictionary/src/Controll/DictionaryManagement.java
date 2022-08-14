package Controll;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


public class DictionaryManagement {
    public static void insertFromCommandline(){
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.println("Nhập số từ vựng :");
        num = sc.nextInt();
        for(int i=0; i<num; i++) {

            System.out.print("Nhập từ tiếng anh: ");
            String engWord = sc.next();
            sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            System.out.print("Nhập nghĩa tiếng việt: ");
            String vieWord = sc.nextLine();

            Word wrd = new Word();
            wrd.setWord_target(engWord);
            wrd.setWord_explain(vieWord);
            Dictionary.WordList.add(wrd);
        }
    }

    public static void updateFile() {
        try {
            Formatter f = new Formatter("D:\\dictionary.txt");

            for (int i = 0; i < Dictionary.WordList.size(); ++i) {
                String s = Dictionary.WordList.get(i).toString();
                f.format("%s\n", s);
            }
            f.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void insertfromFile(){
        try {
            File file = new File("D:\\dictionary.txt");
            Scanner sc = new Scanner(file);
            do {
                String temp = sc.nextLine();

                if (!temp.isEmpty()) {

                    temp = temp.trim();
                    int index = temp.indexOf("  ");
                    String engWord = temp.substring(0,index);
                    String vieWord = temp.substring(index + 2);

                    Word wrd = new Word();
                    wrd.setWord_target(engWord);
                    wrd.setWord_explain(vieWord);
                    Dictionary.WordList.add(wrd);
                }
            }while (sc.hasNextLine());
        } catch(FileNotFoundException e){
            System.out.println("File not found");
            }
        updateFile();
    }

    public static void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        int numWordList = Dictionary.WordList.size();
        String explainWord = "";
        boolean found = false;

        System.out.print("Nhập từ tra cứu: ");
        String Word = sc.next();

        for(int i = 0 ; i < numWordList ; i++){
            if (Dictionary.WordList.get(i).getWord_target().equals(Word)) {
                String s = Dictionary.WordList.get(i).getWord_explain();
                explainWord = s;
                found = true;
            }
        }

        if(found) {
            System.out.printf("Nghĩa của từ %s là: %s\n", Word, explainWord);
        }else {
            System.out.println("Không tìm thấy từ cần tra cứu!");
        }

    }

    public static void dictionaryDelete() {
        Scanner sc = new Scanner(System.in);
        int sizeWordList = Dictionary.WordList.size();
        int indexList = -1;

        System.out.print("Nhập từ cần xóa : ");
        String Word = sc.next();

        for(int i = 0; i < sizeWordList; ++i) {
            if (Dictionary.WordList.get(i).getWord_target().equals(Word)) {
                indexList = i;
            }
        }

        if (indexList > -1) {
            Dictionary.WordList.remove(indexList);
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Từ cần xóa không tồn tại!");
        }
        updateFile();
    }

    public static void dictionaryChanges() {
        Scanner sc = new Scanner(System.in);
        int sizeWordList = Dictionary.WordList.size();
        int indexList = -1;

        System.out.print("Nhập từ cần thay đổi : ");
        String Word1 = sc.next();
        System.out.print("Đổi thành : ");
        String Word2 = sc.next();

        for(int i = 0; i < sizeWordList; ++i) {
            if (Dictionary.WordList.get(i).getWord_target().equals(Word1)) {
                indexList = i;
            }
        }

        if (indexList > -1) {
            Dictionary.WordList.get(indexList).setWord_target(Word2);
            System.out.println("Thay đổi thành công!");
        } else {
            System.out.println("Từ cần thay đổi không tồn tại!");
        }
        updateFile();
    }

    public static ArrayList<String> dictionarySearcher(String s) throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        int l = BinarySearch.binarySearchL(s);
        int r = BinarySearch.binarySearchR(s);

        if (l + 1 > r) {
            result.add("Không tìm thấy kết quả!");
            return result;
        } else {
            for (int i = l + 1; i <= r; ++i) {
                result.add(Dictionary.WordList.get(i).getWord_target());
            }
        }
        return result;
    }

    public static void dictionaryExportToFile() throws SQLException {
        ArrayList<Word> wrd = ConnectToSQL.importDatabase();

        try {
            Formatter f = new Formatter("D:\\dictionary.txt");

            for (int i = 0; i < wrd.size(); ++i) {
                String s = (i + 1) + ":\n" + wrd.get(i).fullWord();
                f.format("%s\n\n", s);
            }

            f.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
