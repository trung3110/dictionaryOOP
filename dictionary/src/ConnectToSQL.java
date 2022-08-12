import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

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

      for (int i = 0; i < s.length; i++) {
        if (s[i].startsWith("@")) {
          if (i == 0) {
            word.setPronunciation(s[i]);
          } else {
            continue;
          }
        } else
          if (s[i].startsWith("<")) {
          continue;
        } else
          if (s[i].startsWith("*")) {
          wordMeans = new VieMeanings();
          wordMeans.setWordType(s[i]);
        } else
          if (s[i].startsWith("-")) {
          wordMeans.setExplain(s[i]);
        } else
          if (s[i].startsWith("=")) {
          wordMeans.addExample(s[i]);
        }
        word.addMeans(wordMeans);
      }

      wordList.add(word);
      if (test == 0) break;
    }

    resultSet.close();
    statement.close();
    connect.close();
    return wordList;
  }
}
