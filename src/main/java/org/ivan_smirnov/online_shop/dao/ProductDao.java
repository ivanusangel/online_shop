package org.ivan_smirnov.online_shop.dao;

import org.ivan_smirnov.online_shop.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/testbd";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";


    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            ResultSet resultSet = runQuery("SELECT * FROM product;");
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


    private ResultSet runQuery(String query) throws SQLException {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
    }
}
