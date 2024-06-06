package spaceplus.view;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.Map;
import com.sun.net.httpserver.HttpExchange;

import spaceplus.pages.JSPFacade;
import spaceplus.view.ViewLog;
import spaceplus.log.Log;
import spaceplus.Handler;
import spaceplus.controller.ControllerFilm;

public class Create extends Handler {
  protected void doGet(HttpExchange exchange) {
    new JSPFacade("/create/create.jsp").dispatch();
  }

  protected void doPost(HttpExchange exchange) {
    String title = exchange.getAttribute("title").toString();
    String rating = exchange.getAttribute("rating").toString();
    String releaseDate = exchange.getAttribute("releaseDate").toString();
    String director = exchange.getAttribute("director").toString();
    String description = exchange.getAttribute("description").toString();
    String duration = exchange.getAttribute("duration").toString();
    String price = exchange.getAttribute("price").toString();
    String language = exchange.getAttribute("language").toString();
    String thumbnail = exchange.getAttribute("thumbnail").toString();

    new Log().info("Creating a film");

    boolean result = new ControllerFilm().create(title, rating, releaseDate, 
        director, description, duration, price, language, thumbnail
    );

    try {
      if (result)  {
        exchange.getResponseHeaders().set("Location", "/spaceplus/browser");
        exchange.sendResponseHeaders(302, -1);
      } else {
        exchange.sendResponseHeaders(500, 0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
