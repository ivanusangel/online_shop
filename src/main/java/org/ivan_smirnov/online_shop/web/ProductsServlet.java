package org.ivan_smirnov.online_shop.web;

import org.ivan_smirnov.online_shop.entity.Product;
import org.ivan_smirnov.online_shop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductsServlet extends HttpServlet {
    private ProductService productService;

    public ProductsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Iterable<Product> productList = productService.getAll();

        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", productList);
        PageGenerator.getPage("products.html", pageVariables, response.getWriter());
    }
}