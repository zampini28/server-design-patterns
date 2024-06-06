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
import spaceplus.model.builder.CustomerBuilder;
import spaceplus.model.dto.Customer;

public class CustomerDAO {
  private final String INSERT = "INSERT INTO customer (name, email, username, password) VALUES (?, ?, ?, ?)";
  private final String UPDATE = "update customer set name = ?, email = ?, username = ?, password = ? where id = ?";
  private final String DELETE = "DELETE FROM customer WHERE id = ?";
  private final String SELECT_ALL = "SELECT * FROM customer";
  private final String SELECT_BY_ID = "SELECT * FROM customer WHERE id = ?";
  private final String SELECT_BY_USERNAME_OR_EMAIL = "SELECT * FROM customer WHERE username = ? or email = ?";
  private final String SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM customer WHERE username = ? AND password = ?";

  public boolean insert(Customer customer) {
    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
      preparedStatement.setString(1, customer.name());
      preparedStatement.setString(2, customer.email());
      preparedStatement.setString(3, customer.username());
      preparedStatement.setString(4, customer.password());
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
     new Log().error("Insert Customer Failed: " + e.getMessage());
      return false;
    }
  }

  public List<Customer> selectAll() {
    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
         ResultSet resultSet = preparedStatement.executeQuery()) {
      List<Customer> customers = new ArrayList<>();
      while (resultSet.next())
        customers.add(new CustomerBuilder()
          .setId(resultSet.getInt("id"))
          .setName(resultSet.getString("name"))
          .setEmail(resultSet.getString("email"))
          .setUsername(resultSet.getString("username"))
          .setPassword(resultSet.getString("password"))
          .setCreatedAt(resultSet.getTimestamp("created_at"))
          .setUpdatedAt(resultSet.getTimestamp("updated_at"))
          .build());
      return customers;
    } catch (SQLException e) {
     new Log().error("Select All Customers Failed: " + e.getMessage());
      return Collections.emptyList();
    }
  }

  public Customer selectById(int id) {
    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
      preparedStatement.setInt(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return new CustomerBuilder()
            .setId(resultSet.getInt("id"))
            .setName(resultSet.getString("name"))
            .setEmail(resultSet.getString("email"))
            .setUsername(resultSet.getString("username"))
            .setPassword(resultSet.getString("password"))
            .setCreatedAt(resultSet.getTimestamp("created_at"))
            .setUpdatedAt(resultSet.getTimestamp("updated_at"))
            .build();
        }
      }
    } catch (SQLException e)  {
     new Log().error("Select Customer Failed: " + e.getMessage());
    }
    return null;
  }

  public boolean update(Customer customer) {
    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
      preparedStatement.setString(1, customer.name());
      preparedStatement.setString(2, customer.email());
      preparedStatement.setString(3, customer.username());
      preparedStatement.setString(4, customer.password());
      preparedStatement.setInt(5, customer.id());
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e)  {
      new Log().error("Update Customer Failed: " + e.getMessage());
      return false;
    }
  }

  public boolean delete(int id) {
    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
      preparedStatement.setInt(1, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e)  {
      new Log().error("Delete Customer Failed: " + e.getMessage());
      return false;
    }
  }

  public boolean exists(Customer customer) {
    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USERNAME_OR_EMAIL)) {
      preparedStatement.setString(1, customer.username());
      preparedStatement.setString(2, customer.email());
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return resultSet.next();
      }
    } catch (SQLException e)  {
      new Log().error("Customer Exists Failed: " + e.getMessage());
      return false;
    }
  }

  public Customer login(Customer customer) {
    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USERNAME_AND_PASSWORD)) {
      preparedStatement.setString(1, customer.username());
      preparedStatement.setString(2, customer.password());
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return new CustomerBuilder()
            .setId(resultSet.getInt("id"))
            .setName(resultSet.getString("name"))
            .setEmail(resultSet.getString("email"))
            .setUsername(resultSet.getString("username"))
            .setPassword(resultSet.getString("password"))
            .setCreatedAt(resultSet.getTimestamp("created_at"))
            .setUpdatedAt(resultSet.getTimestamp("updated_at"))
            .build();
        }
      }
    } catch (SQLException e)  {
      new Log().error("Login Customer Failed: " + e.getMessage());
    }
    return null;
  }
}
