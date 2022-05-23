package com.choo.blog.util;

import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Locale;
import java.util.Map;

public class TextTemplateEngine {
    private static TextTemplateEngine instance = null;
    private TemplateEngine templateEngine;

    private final static String TEMPLATE_LOCAL = "US";

    private TextTemplateEngine() {
        templateEngine = new SpringTemplateEngine();
        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setTemplateMode(TemplateMode.TEXT);
        stringTemplateResolver.setCacheable(false);
        ;
        templateEngine.setTemplateResolver(stringTemplateResolver);
    }

    public static TextTemplateEngine getInstance() {
        if (instance == null) {
            instance = new TextTemplateEngine();
        }
        return instance;
    }


    public String getTemplatefromAttributes(String htmlContent, Map<String, Object> attr) {
        Context context = new Context(new Locale(TEMPLATE_LOCAL));
        if (!CollectionUtils.isEmpty(attr)) {
            attr.forEach((k, v) -> context.setVariable(k, v));
        }
        return templateEngine.process(htmlContent, context);
    }
}
