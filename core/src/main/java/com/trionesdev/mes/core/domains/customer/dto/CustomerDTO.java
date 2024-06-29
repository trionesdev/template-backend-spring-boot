package com.trionesdev.mes.core.domains.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String id;
    private String code;
    private String name;
    private String fullName;
    private String contactName;
    private String contactPhone;
    private String contactAddress;
    private Boolean enabled;
}
