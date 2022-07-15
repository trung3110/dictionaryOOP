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
            System.out.print("Nhập giải thích sang tiếng việt: ");
            String vieWord = sc.nextLine();
            Dictionary.WordList.add(new Word(engWord, vieWord));
        }
    }
}
