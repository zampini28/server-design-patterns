package spaceplus.view;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.Map;
import java.util.List;

import spaceplus.view.ViewLog;
import spaceplus.Handler;
import spaceplus.pages.JSPFacade;
import spaceplus.controller.ControllerFilm;
import spaceplus.model.dto.Film;
import com.sun.net.httpserver.HttpExchange;

public class View extends Handler {
  protected void doGet(HttpExchange exchange) {
    String id = exchange.getAttribute("id").toString();
  try {
    if (id == null) {
      exchange.sendResponseHeaders(400, -1);
    } else {
      Film film = new ControllerFilm().selectById(Integer.parseInt(id));
      exchange.setAttribute("film", film);
      new JSPFacade("/view/view.jsp").dispatch();
    }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpExchange exchange) {
  }
}
