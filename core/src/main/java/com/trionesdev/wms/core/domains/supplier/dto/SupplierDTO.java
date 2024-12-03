package com.trionesdev.wms.core.domains.supplier.dto;

import lombok.Data;

@Data
public class SupplierDTO {
    private String id;
    private String tenantId;
    private String code;
    private String name;
    private String contactName;
    private String contactPhone;
    private String contactFixedTelephone;
    private String contactEmail;
    private String contactAddress;
    private Boolean enabled;
    private String remark;
}
