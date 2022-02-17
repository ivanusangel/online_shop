package org.ivan_smirnov.online_shop.dao;

import org.ivan_smirnov.online_shop.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";


    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (ResultSet resultSet = runQuery("SELECT * FROM product;", null)) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace(); //log
        }
        return products;
    }

    public int getLastIndex() {
        try (ResultSet resultSet = runQuery("SELECT max(id) FROM product;", null)) {
            resultSet.next();
            return resultSet.getInt("id");
        }
        catch (SQLException e) {
            e.printStackTrace(); //log
            return -1;
        }
    }

    public void insertProduct(Product product) {
        product.setId(getLastIndex() + 1);
        try (ResultSet resultSet = runQuery("insert into product (id, name, price) VALUES (?, ?, ?);", product)) {
        }
        catch (SQLException e) {
            e.printStackTrace(); //log
        }
    }

    private ResultSet runQuery(String query, Product product) throws SQLException {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statement = connection.prepareStatement(query);
            if (product != null) {
                statement.setInt(1, product.getId());
                statement.setString(2, product.getName());
                statement.setInt(3, product.getPrice());
            }
            return statement.executeQuery();
    }
}
