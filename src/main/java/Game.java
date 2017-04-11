import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Game {
  private String playerName;
  private int id;
  private int score;

  public Game(String playerName) {
    this.playerName = playerName;
    this.score = 0;
  }

  public String getPlayerName() {
    return this.playerName;
  }

  public int getScore() {
    return this.score;
  }

  public int getId() {
    return this.id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO games (player_name, score) VALUES (:player_name, :score)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("player_name", this.playerName)
        .addParameter("score", this.score)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Game> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM games";
      return con.createQuery(sql)
        .addColumnMapping("player_name", "playerName")
        .executeAndFetch(Game.class);
    }
  }

  @Override
  public boolean equals(Object otherGame) {
    if (!(otherGame instanceof Game)) {
      return false;
    } else {
      Game newGame = (Game) otherGame;
      return newGame.getPlayerName().equals(this.getPlayerName()) && newGame.getId() == this.getId();
    }
  }

  public static Game find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM games WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .addColumnMapping("player_name", "playerName")
        .executeAndFetchFirst(Game.class);
    }
  }

  public void setScore(int levelScore) {
    this.score += levelScore;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE games SET score=:score WHERE id=:id";
        con.createQuery(sql)
        .addParameter("score", this.score)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public static List<Game> getGamesByScore() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM games ORDER BY score DESC";
        return con.createQuery(sql)
          .addColumnMapping("player_name", "playerName")
          .executeAndFetch(Game.class);

    }
  }


}
