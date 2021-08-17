import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        get("/",((request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("Rangers", Ranger.all());
            model.put("")
        })
    },new HandlebarsTemplateEngine());
    }

    private static ModelAndView handle(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("Rangers", Ranger.all());
        model.put("template", "templates/index.hbs");
        return new ModelAndView(model, "index.hbs");
    }
}
