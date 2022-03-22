package org.ivan_smirnov.online_shop.dao;

import org.ivan_smirnov.online_shop.entity.Product;

public interface ProductDao {

    Iterable<Product> findAll();

    Product findById(int id);

    boolean insert(Product product);

    boolean update(Product product);

    boolean delete(int id);
}
