package spaceplus.view;

import com.google.gson.Gson;

import java.io.*;

import java.util.List;

import spaceplus.controller.ControllerFilm;
import spaceplus.model.dto.Film;
import spaceplus.Handler;
import spaceplus.view.ViewLog;
import spaceplus.pages.JSPFacade;
import com.sun.net.httpserver.HttpExchange;
 
public class Browser extends Handler {
  protected void doGet(HttpExchange exchange) {
    String search = exchange.getAttribute("search").toString();
    String mode = exchange.getAttribute("mode").toString();

    if (search == null || mode == null) {
      List<Film> films = new ControllerFilm().selectRandom();
      exchange.setAttribute("films", films);
      exchange.setAttribute("isSearch", false);
    } else {
      List<Film> films = new ControllerFilm().searchQuery(search, mode);
      exchange.setAttribute("films", films);
      exchange.setAttribute("isSearch", true);
    }
    new JSPFacade("/browser/browser.jsp").dispatch();
  }

  protected void doPost(HttpExchange exchange) {
  }
}
