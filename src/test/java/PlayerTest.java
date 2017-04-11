import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class PlayerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Player_instantiatesCorrectly_true() {
    Player newPlayer = new Player("Ryan");
    assertTrue(newPlayer instanceof Player);
  }

  @Test
  public void getName_instantiatesCorrectly_true() {
    Player newPlayer = new Player("Ryan");
    assertEquals("Ryan", newPlayer.getName());
  }

  @Test
  public void save_savesToDB_true() {
    Player player = new Player("Ryan");
    player.save();
    assertTrue(Player.all().get(0).equals(player));
  }

  @Test
  public void find_findsById_true() {
    Player player = new Player("Ryan");
    player.save();
    assertEquals(player, Player.find(player.getId()));
  }

}
