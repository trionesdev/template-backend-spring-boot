package com.trionesdev.template.core.domains.tenant.dto;

import com.trionesdev.template.core.domains.tenant.shared.enums.OrgNodeType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class OrgNodeDTO {
    private String id;
    private String name;
    private String avatar;
    private OrgNodeType type;
    private String nickname;

}
