package com.ms.core.conf.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ms.core.commons.ctx.OperateContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class MPMetaObjectHandler implements MetaObjectHandler {

    @NonNull
    private final OperateContext operateContext;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"createdAt", Date.class, new Date());
        this.strictInsertFill(metaObject,"createdBy", Long.class, operateContext.getOperatorIdAsLong());
        this.strictInsertFill(metaObject,"updatedAt", Date.class, new Date());
        this.strictInsertFill(metaObject,"updatedBy", Long.class, operateContext.getOperatorIdAsLong());
        this.strictInsertFill(metaObject,"deleted", Boolean.class, false);
        this.strictInsertFill(metaObject,"version", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updatedAt", Date.class, new Date());
        this.strictInsertFill(metaObject,"updatedBy", Long.class, operateContext.getOperatorIdAsLong());
    }
}
