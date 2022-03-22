package org.ivan_smirnov.online_shop.dao.jdbc;

import org.ivan_smirnov.online_shop.dao.ProductDao;
import org.ivan_smirnov.online_shop.dao.jdbc.mapper.ProductRowMapper;
import org.ivan_smirnov.online_shop.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String SELECT_ALL_PRODUCTS = "SELECT id, name, price FROM product ORDER BY id;";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT id, name, price FROM product WHERE id = ?;";
    private static final String CREATE_NEW_PRODUCT = "INSERT INTO product (name, price) VALUES (?, ?);";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, price = ? WHERE id = ?;";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?;";
    private static final ProductRowMapper ROW_MAPPER = new ProductRowMapper();
    private final DataSource dataSource;

    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet resultSet = statement.executeQuery()) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(ROW_MAPPER.mapRow(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace(); //log
            throw new RuntimeException("Cannot read DB", e);
        }
    }

    @Override
    public Product findById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)
             ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                Product product = ROW_MAPPER.mapRow(resultSet);
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace(); //log
            throw new RuntimeException("Cannot read DB", e);
        }
    }

    @Override
    public boolean insert(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_NEW_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace(); //log
            throw new RuntimeException("Cannot create product " + product, e);
        }
    }

    @Override
    public boolean update(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)
        ) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getId());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace(); //log
            throw new RuntimeException("Cannot read DB", e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)
        ) {
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace(); //log
            throw new RuntimeException("Cannot read DB", e);
        }
    }

}