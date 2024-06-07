package spaceplus.pages;

import spaceplus.http.HttpRequest;
import spaceplus.http.HttpResponse;

public interface RequestDispatcher {
    public void forward(HttpRequest request, HttpResponse response);
}
