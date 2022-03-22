package org.ivan_smirnov.online_shop;

import org.ivan_smirnov.online_shop.config.PropertyReader;
import org.ivan_smirnov.online_shop.dao.ConnectionFactory;
import org.ivan_smirnov.online_shop.dao.ProductDao;
import org.ivan_smirnov.online_shop.dao.jdbc.JdbcProductDao;
import org.ivan_smirnov.online_shop.service.ContentReader;
import org.ivan_smirnov.online_shop.service.ProductService;
import org.ivan_smirnov.online_shop.web.AddProductServlet;
import org.ivan_smirnov.online_shop.web.DeleteProductServlet;
import org.ivan_smirnov.online_shop.web.ProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.ivan_smirnov.online_shop.web.ResourceServlet;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Array;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        //config
        PropertyReader propertyReader = new PropertyReader("application.properties");
        Properties properties = propertyReader.getProperties();
        ContentReader contentReader = new ContentReader();

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(properties.getProperty("db.name"));
        int ports[] = {Integer.parseInt(properties.getProperty("db.port"))};
        dataSource.setPortNumbers(ports);
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));

        //config dao
        ProductDao productDao = new JdbcProductDao(dataSource);

        //config service
        ProductService productService = new ProductService(productDao);

        //config servlet
        ProductsServlet productServlet = new ProductsServlet(productService);
        AddProductServlet addProductServlet = new AddProductServlet(productService, contentReader);
        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(productService);
        ResourceServlet resourceServlet = new ResourceServlet(contentReader);

        //servlet mapping
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(productServlet), "/products");
        servletContextHandler.addServlet(new ServletHolder(addProductServlet), "/products/update");
        servletContextHandler.addServlet(new ServletHolder(deleteProductServlet), "/products/delete");
        servletContextHandler.addServlet(new ServletHolder(resourceServlet), "/assets/*");

        //start
        Server server = new Server(8080);
        server.setHandler(servletContextHandler);
        server.start();
    }
}
