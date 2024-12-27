package com.trionesdev.template.infrastructure.configuration.spring;

import com.trionesdev.template.infrastructure.configuration.spring.convert.StringToInstantConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToInstantConverter());
    }
}
