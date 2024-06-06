package spaceplus.view;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.Map;

import spaceplus.log.Log;
import spaceplus.Handler;
import spaceplus.controller.ControllerFilm;
import com.sun.net.httpserver.HttpExchange;

public class Remove extends Handler {
  protected void doGet(HttpExchange exchange)  {
    String id = exchange.getAttribute("id").toString();
    new ControllerFilm().remove(id);
    try {
      exchange.getResponseHeaders().set("Location", "/spaceplus/browser");
      exchange.sendResponseHeaders(302, -1);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpExchange exchange)  {
  }
}
