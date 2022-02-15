package org.ivan_smirnov.online_shop.servlet;

import org.ivan_smirnov.online_shop.model.Product;
import org.ivan_smirnov.online_shop.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = findAll();

        response.setStatus(HttpServletResponse.SC_OK);

        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", productList);
        String page = pageGenerator.getPage("products.html", pageVariables);
        response.getWriter().println(page);
    }

    private List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "pie", 100));
        productList.add(new Product(2, "cake", 200));
        return productList;
    }
}