package com.trionesdev.mes.rest.backend.domains.customer.controller.ro;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CustomerUpdateRO {
    private String code;
    @NotBlank
    private String name;
    private String fullName;
    private String contactName;
    private String contactPhone;
    private String contactAddress;
}
