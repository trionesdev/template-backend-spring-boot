package com.trionesdev.wms.rest.backend.domains.dic.controller.impl;

import com.trionesdev.spring.core.audit.AuditLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public AuditLogAspect auditLogAspect() {
        return new AuditLogAspect();
    }

}
