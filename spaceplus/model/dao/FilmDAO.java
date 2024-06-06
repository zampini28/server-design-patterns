package spaceplus.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spaceplus.ConnectionFactory;
import spaceplus.log.Log;
import spaceplus.model.builder.FilmBuilder;
import spaceplus.model.dto.Film;

public class FilmDAO {
    private final String INSERT = "INSERT INTO film (title, rating, release_date, director, " + 
      "description, duration, price, language, thumbnail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE film SET title = ?, rating = ?, release_date = ?, director = ?, " +
      "description = ?, duration = ?, price = ?, language = ?, thumbnail = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM film WHERE id = ?";
    private final String SELECT_ALL = "SELECT * FROM film";
    private final String SELECT_BY_ID = "SELECT * FROM film WHERE id = ?";
    private final String SELECT_RANDOM = "SELECT * FROM film ORDER BY RAND()";
    private final String SELECT_TITLE = "SELECT * FROM film WHERE title LIKE ?";
    private final String SELECT_DIRECTOR = "SELECT * FROM film WHERE director LIKE ?";
    private final String SELECT_DESCRIPTION = "SELECT * FROM film WHERE description LIKE ?";
    private final String SELECT_LANGUAGE = "SELECT * FROM film WHERE language LIKE ?";

    public boolean insert(Film film) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, film.title());
            preparedStatement.setDouble(2, film.rating());
            preparedStatement.setString(3, film.releaseDate());
            preparedStatement.setString(4, film.director());
            preparedStatement.setString(5, film.description());
            preparedStatement.setString(6, film.duration());
            preparedStatement.setDouble(7, film.price());
            preparedStatement.setString(8, film.language());
            preparedStatement.setString(9, film.thumbnail());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            new Log().error("Insert Film Failed" + e.getMessage());
            return false;
        }
    }

    public List<Film> selectAll() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Film> films = new ArrayList<>();
            while (resultSet.next())
                films.add(new FilmBuilder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .rating(resultSet.getDouble("rating"))
                        .releaseDate(resultSet.getString("release_date"))
                        .director(resultSet.getString("director"))
                        .description(resultSet.getString("description"))
                        .duration(resultSet.getString("duration"))
                        .price(resultSet.getDouble("price"))
                        .language(resultSet.getString("language"))
                        .thumbnail(resultSet.getString("thumbnail"))
                        .createdAt(resultSet.getTimestamp("created_at"))
                        .updatedAt(resultSet.getTimestamp("updated_at"))
                        .build());
            return films;
        } catch (SQLException e) {
            new Log().error("Select All Film Failed" + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Film selectById(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return new FilmBuilder()
                            .id(resultSet.getInt("id"))
                            .title(resultSet.getString("title"))
                            .rating(resultSet.getDouble("rating"))
                            .releaseDate(resultSet.getString("release_date"))
                            .director(resultSet.getString("director"))
                            .description(resultSet.getString("description"))
                            .duration(resultSet.getString("duration"))
                            .price(resultSet.getDouble("price"))
                            .language(resultSet.getString("language"))
                            .thumbnail(resultSet.getString("thumbnail"))
                            .createdAt(resultSet.getTimestamp("created_at"))
                            .updatedAt(resultSet.getTimestamp("updated_at"))
                            .build();
            }
        } catch (SQLException e) {
            new Log().error("Select Film By Id Failed" + e.getMessage());
        }
        return null;
    }

    public List<Film> selectRandom() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RANDOM);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Film> films = new ArrayList<>();
            while (resultSet.next())
                films.add(new FilmBuilder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .rating(resultSet.getDouble("rating"))
                        .releaseDate(resultSet.getString("release_date"))
                        .director(resultSet.getString("director"))
                        .description(resultSet.getString("description"))
                        .duration(resultSet.getString("duration"))
                        .price(resultSet.getDouble("price"))
                        .language(resultSet.getString("language"))
                        .thumbnail(resultSet.getString("thumbnail"))
                        .createdAt(resultSet.getTimestamp("created_at"))
                        .updatedAt(resultSet.getTimestamp("updated_at"))
                        .build());
            return films;
        } catch (SQLException e) {
            new Log().error("Select Random Film Failed" + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean update(Film film) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, film.title());
            preparedStatement.setDouble(2, film.rating());
            preparedStatement.setString(3, film.releaseDate());
            preparedStatement.setString(4, film.director());
            preparedStatement.setString(5, film.description());
            preparedStatement.setString(6, film.duration());
            preparedStatement.setDouble(7, film.price());
            preparedStatement.setString(8, film.language());
            preparedStatement.setString(9, film.thumbnail());
            preparedStatement.setInt(10, film.id());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
           new Log().error("Update Film Failed" + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
           new Log().error("Delete Film Failed" + e.getMessage());
            return false;
        }
    }

    public List<Film> selectTitle(String query) {
      try (Connection connection = ConnectionFactory.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TITLE)) {
        preparedStatement.setString(1, "%" + query + "%");
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          List<Film> films = new ArrayList<>();
          while (resultSet.next())
            films.add(new FilmBuilder()
              .id(resultSet.getInt("id"))
              .title(resultSet.getString("title"))
              .rating(resultSet.getDouble("rating"))
              .releaseDate(resultSet.getString("release_date"))
              .director(resultSet.getString("director"))
              .description(resultSet.getString("description"))
              .duration(resultSet.getString("duration"))
              .price(resultSet.getDouble("price"))
              .language(resultSet.getString("language"))
              .thumbnail(resultSet.getString("thumbnail"))
              .createdAt(resultSet.getTimestamp("created_at"))
              .updatedAt(resultSet.getTimestamp("updated_at"))
              .build());
          return films;
      }
    } catch (SQLException e) {
      new Log().error("Select Film By Title Failed" + e.getMessage());
      return Collections.emptyList();
    }
  }

  public List<Film> selectDirector(String query) {
    try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIRECTOR)) {
      preparedStatement.setString(1, "%" + query + "%");
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        List<Film> films = new ArrayList<>();
        while (resultSet.next())
          films.add(new FilmBuilder()
              .id(resultSet.getInt("id"))
              .title(resultSet.getString("title"))
              .rating(resultSet.getDouble("rating"))
              .releaseDate(resultSet.getString("release_date"))
              .director(resultSet.getString("director"))
              .description(resultSet.getString("description"))
              .duration(resultSet.getString("duration"))
              .price(resultSet.getDouble("price"))
              .language(resultSet.getString("language"))
              .thumbnail(resultSet.getString("thumbnail"))
              .createdAt(resultSet.getTimestamp("created_at"))
              .updatedAt(resultSet.getTimestamp("updated_at"))
              .build());
        return films;
      }
    } catch (SQLException e) {
      new Log().error("Select Film By Director Failed" + e.getMessage());
      return Collections.emptyList();
    }
  }

  public List<Film> selectDescription(String query) {
    try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DESCRIPTION)) {
      preparedStatement.setString(1, "%" + query + "%");
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        List<Film> films = new ArrayList<>();
        while (resultSet.next())
          films.add(new FilmBuilder()
              .id(resultSet.getInt("id"))
              .title(resultSet.getString("title"))
              .rating(resultSet.getDouble("rating"))
              .releaseDate(resultSet.getString("release_date"))
              .director(resultSet.getString("director"))
              .description(resultSet.getString("description"))
              .duration(resultSet.getString("duration"))
              .price(resultSet.getDouble("price"))
              .language(resultSet.getString("language"))
              .thumbnail(resultSet.getString("thumbnail"))
              .createdAt(resultSet.getTimestamp("created_at"))
              .updatedAt(resultSet.getTimestamp("updated_at"))
              .build());
        return films;
      }
    } catch (SQLException e) {
      new Log().error("Select Film By Description Failed" + e.getMessage());
      return Collections.emptyList();
    }
  }

  public List<Film> selectLanguage(String query) {
    try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LANGUAGE)) {
      preparedStatement.setString(1, "%" + query + "%");
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        List<Film> films = new ArrayList<>();
        while (resultSet.next())
          films.add(new FilmBuilder()
              .id(resultSet.getInt("id"))
              .title(resultSet.getString("title"))
              .rating(resultSet.getDouble("rating"))
              .releaseDate(resultSet.getString("release_date"))
              .director(resultSet.getString("director"))
              .description(resultSet.getString("description"))
              .duration(resultSet.getString("duration"))
              .price(resultSet.getDouble("price"))
              .language(resultSet.getString("language"))
              .thumbnail(resultSet.getString("thumbnail"))
              .createdAt(resultSet.getTimestamp("created_at"))
              .updatedAt(resultSet.getTimestamp("updated_at"))
              .build());
        return films;
      }
    } catch (SQLException e) {
      new Log().error("Select Film By Language Failed" + e.getMessage());
      return Collections.emptyList();
    }
  }
}
