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

public class GameTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void game_instantiatesCorrectly_true() {
    Game game = new Game("Player");
    assertTrue(game instanceof Game);
  }

  @Test
  public void getters_gettersReturnThings_true() {
    Game game = new Game("Player");
    assertEquals("Player", game.getPlayerName());
    assertEquals(0, game.getScore());
  }

  @Test
  public void save_savesToDB_true() {
    Game game = new Game("Player");
    game.save();
    assertTrue(Game.all().get(0).equals(game));
  }

  @Test
  public void getId_returnsId_true() {
    Game game = new Game("Player");
    game.save();
    assertTrue(game.getId() > 0);
  }

  @Test
  public void find_returnsGameById_game2() {
    Game game1 = new Game("Player1");
    game1.save();
    Game game2 = new Game("Player2");
    game2.save();
    assertEquals(game2, Game.find(game2.getId()));
  }

  @Test
  public void setScore_addsLevelScoreToScore_10() {
    Game game = new Game("Player");
    game.save();
    game.setScore(10);
    assertEquals(10, game.getScore());
    assertEquals(10, Game.find(game.getId()).getScore());
  }

  @Test
  public void getAllScores_returnsListOfScores_true() {
    Game game1 = new Game("Player1");
    game1.save();
    game1.setScore(10);
    Game game2 = new Game("Player2");
    game2.save();
    game2.setScore(40);
    assertEquals(40, Game.getGamesByScore().get(0).getScore());
    assertEquals(10, Game.getGamesByScore().get(1).getScore());
  }

  @Test
  public void calculateScore_returnsLevelScore() {
    Game game = new Game("Player");
    game.save();
    Integer expectedOutcome = 19500;
    assertEquals(expectedOutcome, game.calculateScore(2000, 3000, 20000));
  }

  @Test
  public void getLevelName_returnsLevelNameFromDB_String() {
    Game game = new Game("Player");
    game.save();
    assertEquals("Swordfish", game.getLevelName());
    assertEquals("Find the swordfish.", game.getLevelInstruction());
    int millis = 20000;
    assertEquals(millis, game.getLevelMillis());
    assertEquals("image div placeholder", game.getLevelImageDiv());
    assertEquals("timer div placeholder", game.getLevelTimerDiv());
  }

}
