package spaceplus.model.builder;

import java.sql.Timestamp;

import spaceplus.model.dto.Film;

public class FilmBuilder {
    private Integer id;
    private String title;
    private Double rating;
    private String releaseDate;
    private String director;
    private String description;
    private String duration;
    private Double price;
    private String language;
    private String thumbnail;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public FilmBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public FilmBuilder title(String title) {
        this.title = title;
        return this;
    }

    public FilmBuilder rating(Double rating) {
        this.rating = rating;
        return this;
    }

    public FilmBuilder releaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public FilmBuilder director(String director) {
        this.director = director;
        return this;
    }

    public FilmBuilder description(String description) {
        this.description = description;
        return this;
    }

    public FilmBuilder duration(String duration) {
        this.duration = duration;
        return this;
    }

    public FilmBuilder price(Double price) {
        this.price = price;
        return this;
    }

    public FilmBuilder language(String language) {
        this.language = language;
        return this;
    }

    public FilmBuilder thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public FilmBuilder createdAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public FilmBuilder updatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Film build() {
        return new Film(id, title, rating, releaseDate, director, description, 
            duration, price, language, thumbnail, createdAt, updatedAt);
    }
}
