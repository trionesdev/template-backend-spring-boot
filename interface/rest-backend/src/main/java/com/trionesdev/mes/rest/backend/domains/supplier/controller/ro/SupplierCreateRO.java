package com.trionesdev.mes.rest.backend.domains.supplier.controller.ro;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierCreateRO {
    private String code;
    @NotBlank
    private String name;
    private String fullName;
    private String contactName;
    private String contactPhone;
    private String contactAddress;
}
