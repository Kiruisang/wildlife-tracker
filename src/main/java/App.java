import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Animal;
import models.Sighting;
import spark.QueryParamsMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import org.apache.log4j.BasicConfigurator;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") !=null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model,  "index.hbs");
        }, new HandlebarsTemplateEngine());
        get("/animalsinput", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "animalsinput.hbs");
        }, new HandlebarsTemplateEngine());
        post("/sightings", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String animal = request.queryParams("animal");
            String ranger = request.queryParams("ranger");
            String location = request.queryParams(location);
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String type = request.queryParams("type");

            if (type.equals("animal")){
                Animal animal1 = new Animal(animal);
                animal1.save();
                Sighting newSighting = new Sighting(animal.getId(),location, ranger)
            }
        }));
    }

}
