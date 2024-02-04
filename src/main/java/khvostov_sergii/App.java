package khvostov_sergii;

import java.sql.*;

public class App {

    private static final String URL = "jdbc:mysql://localhost:3306/sql_project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String insertProductsTable = "INSERT INTO products (product_id, name, price, description) VALUES (1, 'Product1', 10.99, 'Description1'), (2, 'Product2', 20.99, 'Description2')";
            statement.executeUpdate(insertProductsTable);

            String insertCustomersTable = "INSERT INTO customers (customer_id, name, phone, email) VALUES (1, 'Customer1', '123456789', 'customer1@example.com'), (2, 'Customer2', '987654321', 'customer2@example.com')";
            statement.executeUpdate(insertCustomersTable);

            String selectProductsTable = "SELECT * FROM products";
            try (ResultSet productsResultSet = statement.executeQuery(selectProductsTable)) {
                while (productsResultSet.next()) {
                    System.out.println("Product ID: " + productsResultSet.getInt("product_id"));
                    System.out.println("Name: " + productsResultSet.getString("name"));
                    System.out.println("Price: " + productsResultSet.getDouble("price"));
                    System.out.println("Description: " + productsResultSet.getString("description"));
                    System.out.println("---------------------");
                }
            }

            String selectCustomersTable = "SELECT * FROM customers";
            try (ResultSet customersResultSet = statement.executeQuery(selectCustomersTable)) {
                while (customersResultSet.next()) {
                    System.out.println("Customer ID: " + customersResultSet.getInt("customer_id"));
                    System.out.println("Name: " + customersResultSet.getString("name"));
                    System.out.println("Phone: " + customersResultSet.getString("phone"));
                    System.out.println("Email: " + customersResultSet.getString("email"));
                    System.out.println("---------------------");
                }
            }

        } catch (SQLException e) {
            System.out.println("Fail db connection");
            e.printStackTrace();
        }
    }
}
