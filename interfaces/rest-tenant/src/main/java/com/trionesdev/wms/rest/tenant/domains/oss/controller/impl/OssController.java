package com.trionesdev.wms.rest.tenant.domains.oss.controller.impl;

import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.commons.exception.TrionesError;
import com.trionesdev.wms.core.domains.oss.service.impl.OSSService;
import com.trionesdev.wms.rest.tenant.domains.oss.controller.ro.FormUploadRO;
import com.trionesdev.wms.rest.tenant.domains.oss.controller.vo.UploadResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.trionesdev.wms.rest.tenant.domains.oss.internal.OSSConstants.OSS_URI;

@Tag(name = "OSS")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = OSS_URI)
public class OssController {
    private final OSSService ossService;

    @Operation(summary = "文件上传(FormData)")
    @PostMapping(value = "upload/form-data", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploadResultVO formUpload(FormUploadRO args) {
        var file = args.getFile();
        try {
            var result = ossService.putObject(file.getOriginalFilename(), file.getInputStream(), file.getContentType(), args.getScene());
            return UploadResultVO.builder().id(result.getId()).url(result.getUrl()).path(result.getPath()).build();
        } catch (Exception e) {
            throw new BusinessException(TrionesError.builder().code("UPLOAD_FAILED").message("文件上传失败").build());
        }
    }

}
