package com.trionesdev.template.core.domains.boss.dto.department;

import com.trionesdev.template.core.domains.boss.dto.user.BossUserDTO;
import lombok.Data;

@Data
public class BossDepartmentMemberDTO {
    private String id;
    private String departmentId;
    private String memberId;
    private BossUserDTO member;
}
