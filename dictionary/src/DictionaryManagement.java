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
            Dictionary.WordList.add(new Word(engWord, vieWord));
        }
    }

    public static void insertfromFile(){

        try {
            File file = new File("src/dictionaries.txt");
            Scanner sc = new Scanner(file);
            do {
                String temp = sc.nextLine();

                if (!temp.isEmpty()) {
                    temp = temp.trim();
                    int index = temp.indexOf("  ");
                    String engWord = temp.substring(0,index);
                    String vieWord = temp.substring(index + 5);

                    Dictionary.WordList.add(new Word(engWord, vieWord));
                }
            }while (sc.hasNextLine());
        } catch(FileNotFoundException e){
            System.out.println("File not found");
            }

    }


}
