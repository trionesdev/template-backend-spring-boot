package com.trionesdev.template.infrastructure.configuration.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.trionesdev.boot.core.autoconfigure.AppProperties;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.context.actor.ActorRoleEnum;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.schema.Column;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class TrionesTenantLineInnerInterceptor implements TenantLineHandler {
    private final AppProperties appProperties;
    private final ActorContext actorContext;

    @Override
    public Expression getTenantId() {
        if (BooleanUtils.isNotTrue(appProperties.getMultiTenant())) {
            return new StringValue("0");
        }
        return new StringValue(actorContext.getTenantId());
    }

    @Override
    public String getTenantIdColumn() {
        return TenantLineHandler.super.getTenantIdColumn();
    }

    @Override
    public boolean ignoreTable(String tableName) {
        if (BooleanUtils.isNotTrue(appProperties.getMultiTenant())) {
            return true;
        }
        if (Objects.equals(ActorRoleEnum.BOSS_USER.name(), actorContext.getRole())) {
            return true;
        }
        return List.of(
"triones_boss_user"
        ).contains(tableName);
    }

    @Override
    public boolean ignoreInsert(List<Column> columns, String tenantIdColumn) {
        return TenantLineHandler.super.ignoreInsert(columns, tenantIdColumn);
    }
}
