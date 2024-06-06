package spaceplus.model.dto;

import java.sql.Timestamp;

public record Film (
    Integer id, String title, Double rating, String releaseDate, 
    String director, String description, String duration, 
    Double price, String language, String thumbnail, 
    Timestamp createdAt, Timestamp updatedAt
) {}
