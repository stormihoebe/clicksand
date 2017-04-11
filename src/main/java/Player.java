import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Player {
  private String name;
  private int id;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO players (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Player> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM players";
      return con.createQuery(sql)
        .executeAndFetch(Player.class);
    }
  }

  @Override
  public boolean equals(Object otherPlayer) {
    if (!(otherPlayer instanceof Player)) {
      return false;
    } else {
      Player newPlayer = (Player) otherPlayer;
      return this.getName().equals(newPlayer.getName()) && this.getId() == newPlayer.getId();
    }
  }

  public static Player find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM players WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Player.class);
    }
  }

}
