package com.trionesdev.wms.core.facade.cloud.oss.impl;

import com.trionesdev.csi.api.oss.OssTemplate;
import com.trionesdev.csi.api.oss.request.OssGetObjectUrlRequest;
import com.trionesdev.csi.api.oss.request.OssPutObjectRequest;
import com.trionesdev.csi.api.oss.response.OssPutObjectResponse;
import com.trionesdev.wms.core.facade.cloud.oss.dto.PutObjectCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OssFacade {
    private final OssTemplate ossTemplate;

    public String putObject(PutObjectCmd cmd) {
        OssPutObjectRequest ossPutObjectRequest = OssPutObjectRequest.builder()
                .objectName(cmd.getObjectName())
                .inputStream(cmd.getInputStream())
                .contentType(cmd.getContentType())
                .build();
        OssPutObjectResponse ossPutObjectResponse = ossTemplate.putObject(ossPutObjectRequest);
        return ossPutObjectResponse.getUrl();
    }

    public String getObjectUrl(String objectName) {
        OssGetObjectUrlRequest request = OssGetObjectUrlRequest.builder().objectName(objectName).build();
        return ossTemplate.getObjectUrl(request);
    }
}
