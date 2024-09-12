package com.trionesdev.wms.infrastructure.conf.app;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(AppProperties.class)
@Configuration
public class AppConfiguration {
}
