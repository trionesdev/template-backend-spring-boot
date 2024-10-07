package com.trionesdev.wms.core.facade.cloud.oss.dto;

import com.trionesdev.csi.api.oss.request.BucketInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.InputStream;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PutObjectCmd extends BucketInfo {
    private String objectName;
    private InputStream inputStream;
    private String contentType;
}
