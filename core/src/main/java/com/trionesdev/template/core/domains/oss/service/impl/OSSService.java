package com.trionesdev.template.core.domains.oss.service.impl;

import com.trionesdev.commons.core.util.FilePathUtils;
import com.trionesdev.template.core.domains.oss.dto.UploadResultDTO;
import com.trionesdev.template.core.facade.cloud.oss.dto.PutObjectCmd;
import com.trionesdev.template.core.facade.cloud.oss.impl.OssFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OSSService {
    private final OssFacade ossFacade;

    public UploadResultDTO putObject(String filename, InputStream input, String contentType, String scene) {
        String objectName = FilePathUtils.pathResolve(scene, FilePathUtils.randomNameDatePath(filename));
        String url = ossFacade.putObject(PutObjectCmd.builder().objectName(objectName).inputStream(input).contentType(contentType).build());
        return UploadResultDTO.builder()
                .id(UUID.randomUUID().toString())
                .url(url)
                .path(objectName)
                .build();
    }

}
