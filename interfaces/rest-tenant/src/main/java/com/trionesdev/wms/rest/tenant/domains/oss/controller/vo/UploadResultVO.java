package com.trionesdev.wms.rest.tenant.domains.oss.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultVO {
    private String id;
    private String url;
    private String path;
}
