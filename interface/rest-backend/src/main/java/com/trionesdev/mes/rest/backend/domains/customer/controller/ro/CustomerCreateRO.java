package com.trionesdev.mes.rest.backend.domains.customer.controller.ro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerCreateRO {
    private String code;
    @NotBlank
    private String name;
    private String fullName;
    private String contactName;
    private String contactPhone;
    private String contactAddress;
}
