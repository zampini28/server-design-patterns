package spaceplus.view;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.Map;

import spaceplus.http.HttpWorker;
import spaceplus.http.HttpRequest;
import spaceplus.http.HttpResponse;

public class Login extends HttpWorker {
  @Override
  public void doGet(HttpRequest request, HttpResponse response) {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    request.getRequestDispatcher("/login/login.jsp").forward(request, response);
  }
}
