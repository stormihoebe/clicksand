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

}
