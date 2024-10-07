package com.trionesdev.wms.core.domains.oss.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDTO {
    private String id;
    private String url;
    private String path;
}
