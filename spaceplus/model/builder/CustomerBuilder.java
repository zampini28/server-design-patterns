package spaceplus.model.builder;

import java.sql.Timestamp;

import spaceplus.model.dto.Customer;

public class CustomerBuilder {
  private Integer id;
  private String name;
  private String email;
  private String username;
  private String password;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  public CustomerBuilder setId(Integer id) {
    this.id = id;
    return this;
  }

  public CustomerBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public CustomerBuilder setEmail(String email) {
    this.email = email;
    return this;
  }

  public CustomerBuilder setUsername(String username) {
    this.username = username;
    return this;
  }

  public CustomerBuilder setPassword(String password) {
    this.password = password;
    return this;
  }

  public CustomerBuilder setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public CustomerBuilder setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  public Customer build() {
    return new Customer(id, name, email, username, password, createdAt, updatedAt);
  }
}
