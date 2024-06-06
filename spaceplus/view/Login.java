package spaceplus.view;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.Map;

import java.io.*;

import spaceplus.log.Log;
import spaceplus.controller.ControllerCustomer;
import spaceplus.pages.JSPFacade;
import spaceplus.Handler;
import com.sun.net.httpserver.HttpExchange;

public class Login extends Handler {
  public void doGet(HttpExchange exchange) {
    new JSPFacade("/login/login.jsp").dispatch();
  }

  public void doPost(HttpExchange exchange) {
    var reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
    Gson gson = new Gson();
    Map<String, String> requestBody = gson.fromJson(reader, Map.class);

    String username = requestBody.get("username");
    String password = requestBody.get("password");

    new Log().info("Username is " + username);
    new Log().info("Password is " + password);

    boolean result = new ControllerCustomer().login(username, password);

    try {
      if (result)
        exchange.sendResponseHeaders(204, 0);
      else
        exchange.sendResponseHeaders(401, 0);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
