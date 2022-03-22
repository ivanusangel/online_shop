package org.ivan_smirnov.online_shop.dao.jdbc.mapper;

import org.ivan_smirnov.online_shop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper {
    public Product mapRow(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .price(resultSet.getDouble("price"))
                .build();
    }
}
