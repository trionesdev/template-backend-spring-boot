package com.trionesdev.template.rest.tenant.domains.oss.controller.ro;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FormUploadRO {
    private MultipartFile file;
    private String scene;
}
