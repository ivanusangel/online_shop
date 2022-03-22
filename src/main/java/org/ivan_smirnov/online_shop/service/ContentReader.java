package org.ivan_smirnov.online_shop.service;

import java.io.*;

public class ContentReader {

    public static void readFileAndPoolToWriter(String uri, OutputStream os) throws IOException {
        if(uri.startsWith("/")) {
            uri = uri.substring(1, uri.length());
        }
        try (InputStream recourse = ContentReader.class.getClassLoader().getResourceAsStream(uri);
                BufferedInputStream reader = new BufferedInputStream(recourse)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = reader.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}
