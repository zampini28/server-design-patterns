package spaceplus.view;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import com.sun.net.httpserver.HttpExchange;
import java.util.Map;

import spaceplus.log.Log;
import spaceplus.Handler;
import spaceplus.pages.JSPFacade;
import spaceplus.controller.ControllerFilm;
import spaceplus.model.dto.Film;

public class Update extends Handler {
  protected void doGet(HttpExchange exchange) {
    String id = exchange.getAttribute("id").toString();
    Film film = new ControllerFilm().selectById(id);
    exchange.setAttribute("film", film);
    new JSPFacade("/update/update.jsp").dispatch();
  }

  protected void doPost(HttpExchange exchange) {
    try {
      String id = exchange.getAttribute("id").toString();
      String title = exchange.getAttribute("title").toString();
      String rating = exchange.getAttribute("rating").toString();
      String releaseDate = exchange.getAttribute("releaseDate").toString();
      String director = exchange.getAttribute("director").toString();
      String description = exchange.getAttribute("description").toString();
      String duration = exchange.getAttribute("duration").toString();
      String price = exchange.getAttribute("price").toString();
      String language = exchange.getAttribute("language").toString();
      String thumbnail = exchange.getAttribute("thumbnail").toString();

    new Log().info("updating a film");

      boolean result = new ControllerFilm().update(id, title, rating, releaseDate, 
          director, description, duration, price, language, thumbnail
      );

      if (result)  {
        exchange.getResponseHeaders().set("Location", "/spaceplus/view?id=" + id);
        exchange.sendResponseHeaders(302, -1);
      } else {
        exchange.sendResponseHeaders(500, 0);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
