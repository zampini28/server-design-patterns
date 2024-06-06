package spaceplus.view;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import spaceplus.model.dto.Film;
import spaceplus.controller.ControllerFilm;
import spaceplus.pages.JSPFacade;
import spaceplus.Handler;

import com.sun.net.httpserver.HttpExchange;

public class Home extends Handler {
  public void doGet(HttpExchange exchange) {
    List<Film> films = new ControllerFilm().selectRandom();
    exchange.setAttribute("films", films);
    new JSPFacade("/home/home.jsp").dispatch();
  }

  public void doPost(HttpExchange exchange) {
    List<Film> films = new ControllerFilm().selectRandom();
    exchange.setAttribute("films", films);
    new JSPFacade("/home/home.jsp").dispatch();
  }
}
