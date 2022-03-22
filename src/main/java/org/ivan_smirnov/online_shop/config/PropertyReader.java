package org.ivan_smirnov.online_shop.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private Properties properties;

    public PropertyReader(String path) {
        properties = new Properties();
        readProperties(path);
    }

    private void readProperties(String fileName) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read properties", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return new Properties(properties);
    }
}
