import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    // Use this setting only for development
    externalStaticFileLocation(String.format("%s/src/main/resources/public", System.getProperty("user.dir")));

    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("topScores", Game.getTopScores());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/start-game", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String playerName = request.queryParams("name");
      Game newGame = new Game(playerName);
      newGame.save();
      request.session().attribute("currentGame", newGame);
      request.redirect("/level-1");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/level-1", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/level-1.vtl");
      request.session().attribute("currentGame");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
