package org.ivan_smirnov.online_shop.main;

import java.util.HashMap;
import java.util.Map;

public class WebAppProperties {
    private static Map<String, String> properties = new HashMap<>();

    static {
        properties.put("webAppPath","src/main/resources");
    }

    public static String getProperty(String key) {
        return properties.get(key);
    }
}
