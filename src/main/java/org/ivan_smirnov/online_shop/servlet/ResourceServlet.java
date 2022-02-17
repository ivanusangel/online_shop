package org.ivan_smirnov.online_shop.servlet;

import org.ivan_smirnov.online_shop.service.ContentReader;
import org.ivan_smirnov.online_shop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ContentReader.doResponse(request.getPathInfo(), response);
    }
}