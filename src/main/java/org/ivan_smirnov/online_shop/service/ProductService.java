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
//        List<Product> productList = new ArrayList<>();
//        productList.add(new Product(1, "pie", 100));
//        productList.add(new Product(2, "cake", 200));
//        return productList;
        return productDao.findAll();
    }
}
