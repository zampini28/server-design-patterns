package spaceplus.view;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;

import java.util.Map;

import spaceplus.log.Log;
import spaceplus.Handler;
import spaceplus.pages.JSPFacade;
import com.sun.net.httpserver.HttpExchange;
import spaceplus.controller.ControllerCustomer;

public class Signup extends Handler {
  public void doGet(HttpExchange exchange) {
    new JSPFacade("/signup/signup.jsp").dispatch();
  }

  public void doPost(HttpExchange exchange) {
    var reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
    Gson gson = new Gson();
    Map<String, String> requestBody = gson.fromJson(reader, Map.class);

    String name = requestBody.get("name");
    String email = requestBody.get("email");
    String username = requestBody.get("username");
    String password = requestBody.get("password");

    new Log().info("Name is " + name);
    new Log().info("Email is " + email);
    new Log().info("Username is " + username);
    new Log().info("Password is " + password);

    boolean result = new ControllerCustomer().signup(name, email, username, password);

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
