package spaceplus.controller;

import java.util.Collections;
import java.util.List;

import spaceplus.log.Log;

import spaceplus.model.builder.FilmBuilder;
import spaceplus.model.dao.FilmDAO;
import spaceplus.model.dto.Film;

public class ControllerFilm {

  public List<Film> selectRandom() {
    return new FilmDAO().selectRandom();
  }

  public Film selectById(int id) {
    return new FilmDAO().selectById(id);
  }

  public Film selectById(String id) {
    return selectById(Integer.parseInt(id));
  }

  public boolean create(String title, String rating, String releaseDate, String director, 
      String description, String duration, String price, String language, String thumbnail) {
    try {
      Film film = new FilmBuilder()
        .title(title)
        .rating(Double.parseDouble(rating))
        .releaseDate(releaseDate)
        .director(director)
        .description(description)
        .duration(duration)
        .price(Double.parseDouble(price))
        .language(language)
        .thumbnail(thumbnail)
        .build();
      return new FilmDAO().insert(film);
    } catch (Exception e) {
      new Log().error("Failed to parse argument when creating film");
      return false;
    }
  }

  public boolean update(String id, String title, String rating, String releaseDate, String director, 
      String description, String duration, String price, String language, String thumbnail) {
    try {
      Film film = new FilmBuilder()
        .id(Integer.parseInt(id))
        .title(title)
        .rating(Double.parseDouble(rating))
        .releaseDate(releaseDate)
        .director(director)
        .description(description)
        .duration(duration)
        .price(Double.parseDouble(price))
        .language(language)
        .thumbnail(thumbnail)
        .build();
      return new FilmDAO().update(film);
    } catch (Exception e) {
     new Log().error("Failed to parse argument when update film");
      return false;
    }
  }

  public void remove(String id) {
    try {
      boolean result = new FilmDAO().delete(Integer.parseInt(id));
      if (!result) new Log().error("Failed when deleting film");
    } catch (Exception e) {
      new Log().error("Failed to parse argument when deleting film");
    }
  }

  public List<Film> searchQuery(String search, String mode) {
    return switch (mode) {
      case "Título" -> new FilmDAO().selectTitle(search);
      case "Diretor" -> new FilmDAO().selectDirector(search);
      case "Descrição" -> new FilmDAO().selectDescription(search);
      case "Linguagem" -> new FilmDAO().selectLanguage(search);
      default -> Collections.emptyList();
    };
  }
}
