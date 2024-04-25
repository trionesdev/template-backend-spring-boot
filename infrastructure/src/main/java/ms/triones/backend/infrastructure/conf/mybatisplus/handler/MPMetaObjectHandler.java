package ms.triones.backend.infrastructure.conf.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.trionesdev.commons.context.actor.ActorContext;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class MPMetaObjectHandler implements MetaObjectHandler {
    private final ActorContext actorContext;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"createdAt", Instant.class, Instant.now());
        this.strictInsertFill(metaObject,"createdBy", String.class, actorContext.getActorId());
        this.strictInsertFill(metaObject,"updatedAt", Instant.class, Instant.now());
        this.strictInsertFill(metaObject,"updatedBy", String.class, actorContext.getActorId());
        this.strictInsertFill(metaObject,"deleted", Boolean.class, false);
        this.strictInsertFill(metaObject,"version", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updatedAt", Instant.class, Instant.now());
        this.strictInsertFill(metaObject,"updatedBy", String.class, actorContext.getActorId());
    }
}
