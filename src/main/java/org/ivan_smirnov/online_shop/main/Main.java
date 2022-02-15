package org.ivan_smirnov.online_shop.main;

import org.ivan_smirnov.online_shop.servlet.ProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        ProductsServlet allRequestsServlet = new ProductsServlet();

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(allRequestsServlet), "/products");

        Server server = new Server(8080);
        server.setHandler(servletContextHandler);

        server.start();
    }
}