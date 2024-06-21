package com.trionesdev.mes.domain.core.domains.perm.entity;

import com.trionesdev.mes.domain.core.domains.perm.repository.po.PolicyPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
    private String appIdentifier;
    private String client;
    private PolicyPO.ObjType grantObjType;
    private String grantObjId;
    private List<Permission> permissions;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Permission {
        private String resObj;
        private String resAct;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Permission that = (Permission) o;
            return Objects.equals(resObj, that.resObj) && Objects.equals(resAct, that.resAct);
        }

        @Override
        public int hashCode() {
            return Objects.hash(resObj, resAct);
        }
    }
}
