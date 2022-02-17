package org.ivan_smirnov.online_shop.service;

import org.ivan_smirnov.online_shop.main.WebAppProperties;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ContentReader {

    public static void doResponse(String uri, HttpServletResponse response) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(WebAppProperties.getProperty("webAppPath") + uri))) {

            String line;
            response.setStatus(HttpServletResponse.SC_OK);
            Writer writer = response.getWriter();
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }

        } catch (FileNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            e.printStackTrace(); //log
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();  //log
        }
    }
}
