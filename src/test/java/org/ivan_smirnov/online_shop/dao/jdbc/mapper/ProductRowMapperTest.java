package org.ivan_smirnov.online_shop.dao.jdbc.mapper;

import org.ivan_smirnov.online_shop.entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRowMapperTest {

    @Test
    void mapRow() throws SQLException {
        ProductRowMapper productRowMapper = new ProductRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        Product expectedProduct = Product.builder()
                .id(10)
                .name("test product")
                .price(13.25)
                .build();

        when(resultSet.getInt("id")).thenReturn(10);

        assertEquals(productRowMapper.mapRow(resultSet), expectedProduct);

    }
}