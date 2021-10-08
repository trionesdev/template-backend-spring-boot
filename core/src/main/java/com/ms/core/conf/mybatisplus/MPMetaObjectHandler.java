package com.ms.core.conf.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.moensun.commons.context.operator.OperateContext;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class MPMetaObjectHandler implements MetaObjectHandler {

    private final OperateContext operateContext;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"createdAt", Instant.class, Instant.now());
        this.strictInsertFill(metaObject,"createdBy", Long.class, operateContext.getOperatorIdAsLong());
        this.strictInsertFill(metaObject,"updatedAt", Instant.class, Instant.now());
        this.strictInsertFill(metaObject,"updatedBy", Long.class, operateContext.getOperatorIdAsLong());
        this.strictInsertFill(metaObject,"deleted", Boolean.class, false);
        this.strictInsertFill(metaObject,"version", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updatedAt", Instant.class, Instant.now());
        this.strictInsertFill(metaObject,"updatedBy", Long.class, operateContext.getOperatorIdAsLong());
    }
}
