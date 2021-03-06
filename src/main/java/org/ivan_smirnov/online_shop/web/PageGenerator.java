package org.ivan_smirnov.online_shop.web;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;


public class PageGenerator {
    //    private static final String HTML_PATH = "src/main/resources/templates";
    private static final Configuration CONFIG = initConfiguration();


    public static void getPage(String fileName, Map<String, Object> data, Writer writer) {
        try {
            Template template = CONFIG.getTemplate(fileName);
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();//log
            throw new RuntimeException("Cannot generate page " + fileName, e);
        }
    }

    private static Configuration initConfiguration() {
        Configuration config = new Configuration(Configuration.VERSION_2_3_31);
        final ClassTemplateLoader loader = new ClassTemplateLoader(PageGenerator.class, "/templates");
        config.setTemplateLoader(loader);
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return config;
    }
}
