package org.ivan_smirnov.online_shop.web;

import org.ivan_smirnov.online_shop.service.ContentReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResourceServlet extends HttpServlet {
    private static final String ASSETS_PATH = "assets";

    ContentReader contentReader;

    public ResourceServlet(ContentReader contentReader) {
        this.contentReader = contentReader;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            ContentReader.readFileAndPoolToWriter(ASSETS_PATH + request.getPathInfo(), response.getOutputStream());
        } catch (FileNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            e.printStackTrace(); //log
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();  //log
        }
    }
}