package spaceplus.controller;

import spaceplus.model.dao.CustomerDAO;
import spaceplus.model.dto.Customer;
import spaceplus.model.builder.CustomerBuilder;

public class ControllerCustomer {
  public boolean login(String username, String password) {
    Customer customer = new CustomerBuilder()
      .setUsername(username)
      .setPassword(password)
      .build();
    return (new CustomerDAO().login(customer) != null);
  }

  public boolean signup(String name, String email, String username, String password) {
    Customer customer = new CustomerBuilder()
      .setName(name)
      .setEmail(email)
      .setUsername(username)
      .setPassword(password)
      .build();
    return new CustomerDAO().insert(customer);
  }
}
