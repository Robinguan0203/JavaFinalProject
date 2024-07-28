package com.fwrp.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import java.io.Writer;

public class ThymeleafUtil {
    private static TemplateEngine engine;

    public static void initialize(ServletContext servletContext) {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix("/views");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver);
    }

    public static void process(String template, IContext context, Writer writer) {
        if (engine == null) {
            throw new IllegalStateException("TemplateEngine has not been initialized. Call initialize() first.");
        }
        engine.process(template, context, writer);
    }
}
