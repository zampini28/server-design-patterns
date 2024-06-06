package spaceplus.model.dto;

import java.sql.Timestamp;

public record Customer (
    Integer id, String name, String email, String username, 
    String password, Timestamp createdAt, Timestamp updatedAt
) {
  @Override
  public String toString() {
    return "ID...........: " + this.id +
           "\nName.........: " + this.name +
           "\nEmail........: " + this.email +
           "\nUsername.....: " + this.username +
           "\nPassword.....: " + this.password +
           "\nCreated_at...: " + this.createdAt +
           "\nUpdated_at...: " + this.updatedAt;
  }
}
