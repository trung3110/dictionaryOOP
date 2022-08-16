package Controll;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ConnectToSQL {
  static final String url = "jdbc:mysql://127.0.0.1:3306/dictionary";
  static final String url1 = "jdbc:mysql://127.0.0.1:3306/loltoan";
  static String password = "2417116qaz";
  static String username = "root";
  static ArrayList<Word> wordList = new ArrayList<Word>();

  public static ArrayList<Word> importDatabase() throws SQLException {
    Connection connect = DriverManager.getConnection(url1, username, password);
    Statement statement = connect.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM trantoan");

    int test = 50000;
    while (resultSet.next()) {
      //--test;
      String idx = resultSet.getString("idx");
      String wordtarget = resultSet.getString("word");
      String detail = resultSet.getString("detail");

      String[] s1 = null;
      s1 = detail.split(">");
      Word word = new Word();
      word.setWord_target(wordtarget.toLowerCase());
      VieMeanings wordMean = new VieMeanings();

      int n = s1.length;
      String res = "";
      String[] s = Arrays.copyOf( s1 , n + 1);
      s[n] = ".";

      int d = 0;
      int dem = 0;
      for (int i = 0; i < n; i++) {

        if (s[i].startsWith("@")) {
            ++d;
            s[i] = s[i].replace("<br /", "");
            s[i] = s[i].replace("</Q", "");
            s[i] = s[i].replace("@", "");
            if ( d == 1 ) {
              word.setPronunciation(s[i]);
            } else {
              wordMean.setWordType(s[i]);
            }
            dem = 0;
        }

        if (s[i].startsWith("-")) {
          ++dem;
          s[i] = s[i].replace("<br /", "");
          s[i] = s[i].replace("</Q", "");
          s[i] = s[i].replace("- ", "");
          res =  "Ý nghĩa " + dem + " : " + s[i] + "\n";


          if (!s[i + 1].startsWith("=")) {
            wordMean.addExplain(res);
            res = "";
          } else {
            res += "Ví dụ :";
          }

          if ( !s[i + 1].startsWith("-") && !s[i + 1].startsWith("=") ) {
            word.addMeans(wordMean);
            wordMean = new VieMeanings();
            dem = 0;
          }

          if ( s[i - 1].startsWith("@") && d > 1) {
            wordMean.addExplain(res);
            res = "";
            word.addMeans(wordMean);
            wordMean = new VieMeanings();
            dem = 0;
          }

        }

        if (s[i].startsWith("=")) {
          s[i] = s[i].replace("<br /", "");
          s[i] = s[i].replace("+", " :");
          s[i] = s[i].replace("</Q", "");
          s[i] = s[i].replace("=", "+, ");

          res += "\n\t" + s[i];

          if ( !s[i + 1].startsWith("=") ) {
            res += "\n";
            wordMean.addExplain(res);
            res = "";

            if ( !s[i + 1].startsWith("-") ) {
              word.addMeans(wordMean);
              dem = 0;
              wordMean = new VieMeanings();
            }
          }
        }

        if (s[i].startsWith("*")) {
          //wordMeans = new VieMeanings();
          s[i] = s[i].replace("<br /", "");
          s[i] = s[i].replace("</Q", "");
          s[i] = s[i].replace("*  ", "");
          wordMean.setWordType(s[i]);
          dem = 0;
        }
      }

      if ( word != null ) {
        wordList.add(word);
      }

      if (test == 0) break;
    }

    resultSet.close();
    statement.close();
    connect.close();

    Collections.sort(wordList, new Comparator<Word>() {
      @Override
      public int compare(Word wrd1, Word wrd2) {
        if (wrd1.getWord_target().compareTo(wrd2.getWord_target()) <= 0 ) {
          return 1;
        } else {
          return 0;
        }
      }
    });

    return wordList;
  }

  public static void InsertDatabase(Word wrd)  {

    int sz = Dictionary.WordList.size();

    try (Connection connection = DriverManager.getConnection(url1, username, password)){
      Statement stmt;
      stmt = connection.createStatement();

      String sql = "INSERT INTO trantoan (idx, word, detail) VALUES (?, ?, ?)";
      PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pstmt.setString(2, wrd.getWord_target());
      pstmt.setInt(1, sz + 1);
      pstmt.setString(3, wrd.fullWord());
      pstmt.executeUpdate();

      System.out.println("Inserted records into the table...");
      System.out.println("Insert data success");

    } catch(SQLException se){
      se.printStackTrace();
    }
    System.out.println("Done!");
  }

  public static void DeleteDatabase(String wrd)  {

    try (Connection connection = DriverManager.getConnection(url1, username, password)){
      Statement stmt;
      stmt = connection.createStatement();

      String sql = "DELETE FROM trantoan WHERE word = (?)";
      PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pstmt.setString(1, wrd);
      pstmt.executeUpdate();

      System.out.println("Inserted records into the table...");
      System.out.println("Insert data success");

    } catch(SQLException se){
      se.printStackTrace();
    }
    System.out.println("Done!");
  }

  public static void DeleteTypeDatabase(String wrd1, String wrd2)  {

    try (Connection connection = DriverManager.getConnection(url1, username, password)){
      Statement stmt;
      stmt = connection.createStatement();

      String sql = "UPDATE trantoan SET detail = (?) WHERE word = (?)";
      PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pstmt.setString(1, wrd1);
      pstmt.setString(2, wrd2);
      pstmt.executeUpdate();

      System.out.println("Inserted records into the table...");
      System.out.println("Insert data success");

    } catch(SQLException se){
      se.printStackTrace();
    }
    System.out.println("Done!");
  }

  public static void EditDatabase() {
  }

}
