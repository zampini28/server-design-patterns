package spaceplus.view;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;

import spaceplus.http.HttpWorker;
import spaceplus.http.HttpRequest;
import spaceplus.http.HttpResponse;

public class Home extends HttpWorker {

  @Override
  public void doGet(HttpRequest request, HttpResponse response) {
      try {
          response.setContentType("text/html");
          response.setCharacterEncoding("UTF-8");

          List<String> list = new ArrayList<>();

          list.add("eu");
          list.add("sou");
          list.add("muito");
          list.add("foda!");

          request.setAttribute("text", list);

          request.getRequestDispatcher("/home/home.jsp").forward(request, response);
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
  }
}
