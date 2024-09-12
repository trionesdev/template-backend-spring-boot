package com.trionesdev.wms.infrastructure.conf.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "triones.app")
public class AppProperties {
    private Boolean multiTenant;
}
