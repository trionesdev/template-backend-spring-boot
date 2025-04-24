package com.trionesdev.template.core.domains.org.dto;

import com.trionesdev.template.core.domains.org.shared.enums.OrgNodeType;
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
