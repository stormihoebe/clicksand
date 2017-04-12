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
  private int levelId;

  public Game(String playerName) {
    this.playerName = playerName;
    this.score = 0;
    this.levelId = 1;
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

  public int getLevelId() {
    return this.levelId;
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

  public Integer calculateScore(long levelStart, long levelEnd, long levelMillis) {
    long timeDifference = levelEnd - levelStart;
    if (timeDifference > levelMillis) {
      timeDifference = levelMillis;
    }
    Integer score = (int) (long) (levelMillis - timeDifference + 500);
    return score;
  }

  public String getLevelName() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT name FROM levels WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id", this.levelId)
        .executeAndFetchFirst(String.class);
    }
  }

  public String getLevelInstruction() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT instruction FROM levels WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id", this.levelId)
        .executeAndFetchFirst(String.class);
    }
  }

  public int getLevelMillis() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT millis FROM levels WHERE id = :id";
      return Integer.parseInt(con.createQuery(sql)
      .addParameter("id", this.levelId)
      .executeAndFetchFirst(String.class));
    }
  }

  public String getLevelImageDiv() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT image_div FROM levels WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id", this.levelId)
        .executeAndFetchFirst(String.class);
    }
  }

  public String getLevelTimerDiv() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT timer_div FROM levels WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id", this.levelId)
        .executeAndFetchFirst(String.class);
    }
  }
}
