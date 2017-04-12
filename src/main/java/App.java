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
      model.put("topScores", Game.getGamesByScore());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/start-game", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String playerName = request.queryParams("name");
      Game newGame = new Game(playerName);
      newGame.save();
      request.session().attribute("game", newGame);
      String url = String.format("levels/%d", newGame.getLevelId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/levels/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Game currentGame = request.session().attribute("game");
      long endTime = System.currentTimeMillis();
      long startTime = request.session().attribute("startTime");
      int levelScore = currentGame.calculateLevelScore(startTime, endTime, currentGame.getLevelMillis());
      model.put("levelScore", levelScore);
      currentGame.setScore(levelScore);
      model.put("game", currentGame);
      int lastLevel =  currentGame.getLevelId();
      model.put("lastLevel", lastLevel);
      currentGame.incrementLevel();
      request.session().attribute("game");
      model.put("template", "templates/level-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/levels/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/level.vtl");
      long startTime = System.currentTimeMillis();
      request.session().attribute("startTime", startTime);
      model.put("game", request.session().attribute("game"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
