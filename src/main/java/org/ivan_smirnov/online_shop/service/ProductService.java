package org.ivan_smirnov.online_shop.service;

import org.ivan_smirnov.online_shop.dao.ProductDao;
import org.ivan_smirnov.online_shop.entity.Product;

import java.util.ArrayList;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Iterable<Product> getAll() {
        try {
            return productDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();//log
            return new ArrayList<Product>();
        }
    }

    public Product getProductById(int id) {
        return productDao.findById(id);
    }

    public void save(Product product) {
        if (product.getId() == 0) {
            productDao.insert(product);
        } else {
            productDao.update(product);
        }
    }

    public void delete(int id) {
        productDao.delete(id);
    }
}
