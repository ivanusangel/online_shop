package org.ivan_smirnov.online_shop.main;

import org.ivan_smirnov.online_shop.dao.ProductDao;
import org.ivan_smirnov.online_shop.service.ProductService;
import org.ivan_smirnov.online_shop.servlet.AddProductServlet;
import org.ivan_smirnov.online_shop.servlet.ProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.ivan_smirnov.online_shop.servlet.ResourceServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        //config dao
        ProductDao productDao = new ProductDao();

        //config service
        ProductService productService = new ProductService(productDao);

        //config servlet
        ProductsServlet productServlet = new ProductsServlet(productService);
        AddProductServlet addProductServlet = new AddProductServlet(productService);
        ResourceServlet resourceServlet = new ResourceServlet();

        //servlet mapping
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(productServlet), "/products");
        servletContextHandler.addServlet(new ServletHolder(addProductServlet), "/products/add");
        servletContextHandler.addServlet(new ServletHolder(resourceServlet), "/*");

        //start
        Server server = new Server(8080);
        server.setHandler(servletContextHandler);

        server.start();
    }
}
