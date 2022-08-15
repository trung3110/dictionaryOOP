package Controll;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ConnectToSQL {
  static final String url = "jdbc:mysql://127.0.0.1:3306/dictionary";
  static String password = "2417116qaz";
  static String username = "root";
  static ArrayList<Word> wordList = new ArrayList<>();

  public static ArrayList<Word> importDatabase() throws SQLException {
    Connection connect = DriverManager.getConnection(url, username, password);
    Statement statement = connect.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_edict");

    int test = 30;
    while (resultSet.next()) {
      --test;
      String idx = resultSet.getString("idx");
      String word_target = resultSet.getString("word");
      String detail = resultSet.getString("detail");

      String[] s;
      s = detail.split(">");
      Word word = new Word();
      word.setWord_target(word_target.toLowerCase());
      VieMeanings wordMeans = new VieMeanings();

      int n = s.length;
      String res = "";
      s = Arrays.copyOf( s , n + 1);
      s[n] = ".";

      for (int i = 0; i < n; i++) {

        if (s[i].startsWith("@")) {
            s[i] = s[i].replace("<br /", "");
            s[i] = s[i].replace("@", "");
            word.setPronunciation(s[i]);
        }

        if (s[i].startsWith("-")) {
          s[i] = s[i].replace("<br /", "");
          s[i] = s[i].replace("- ", "");
          res = "_Ý nghĩa : " + s[i] + "\n" + "Ví dụ :";

          if (!s[i + 1].startsWith("=")) {
            res += "\n";
            wordMeans.addExplain(res);
            res = "";
          }
        }

        if (s[i].startsWith("=")) {
          s[i] = s[i].replace("<br /", "");
          s[i] = s[i].replace("+", " :");
          s[i] = s[i].replace("=", "+, ");
          res += "\n\t" + s[i];

          if ( !s[i + 1].startsWith("=") ) {
            res += "\n";
            wordMeans.addExplain(res);
            res = "";

            if ( !s[i + 1].startsWith("-") ) {
              //if ( word.getMeanings() == null ) System.out.println("vc");
              word.addMeans(wordMeans);
              //System.out.println( word.getMeanings().size() );
              wordMeans = new VieMeanings();
            }
          }
        }

        if (s[i].startsWith("*")) {
          //wordMeans = new VieMeanings();
          s[i] = s[i].replace("<br /", "");
          s[i] = s[i].replace("*  ", "");
          wordMeans.setWordType(s[i]);
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
    return wordList;
  }

  public static void InsertDatabase(Word wrd)  {

    int sz = Dictionary.WordList.size();

    try (Connection connection = DriverManager.getConnection(url, username, password)){
      Statement stmt;
      stmt = connection.createStatement();

      String sql = "INSERT INTO tbl_edict (idx, word, detail) VALUES (?, ?, ?)";
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

    try (Connection connection = DriverManager.getConnection(url, username, password)){
      Statement stmt;
      stmt = connection.createStatement();

      String sql = "DELETE FROM tbl_edict WHERE word = (?)";
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

    try (Connection connection = DriverManager.getConnection(url, username, password)){
      Statement stmt;
      stmt = connection.createStatement();

      String sql = "UPDATE tbl_edict SET detail = (?) WHERE word = (?)";
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

  /*public static void EditDatabase() {
    Word wrd;
    for (int i = 0)
  }*/

}
