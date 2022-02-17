package org.ivan_smirnov.online_shop.service;

import org.ivan_smirnov.online_shop.dao.ProductDao;
import org.ivan_smirnov.online_shop.model.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAll() {
        return productDao.findAll();
    }

    public void addProduct(Product product) {
    }
}
