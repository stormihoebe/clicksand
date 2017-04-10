import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/clickbait_game_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      // String deleteLevelQuery = "DELETE FROM levels *;";
      // con.createQuery(deleteLevelQuery).executeUpdate();
      // String deleteGameQuery = "DELETE FROM games *;";
      // con.createQuery(deleteGameQuery).executeUpdate();
      String deletePlayerQuery = "DELETE FROM players *;";
      con.createQuery(deletePlayerQuery).executeUpdate();
    }
  }
}
