package com.trionesdev.mes.domain.core.domains.manufacture.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.entity.BaseLogicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "manufacture_order_task")
@Alias("task")
public class ManufactureOrderTaskPO extends BaseLogicEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String orderId;
    private String processCode; //工序名称
    private BigDecimal ratio; //配比
    private BigDecimal planQuantity; //计划数量
    private BigDecimal goodQuantity; //良品数量
    private BigDecimal defectiveQuantity; //不良品数量
    private Instant planStartTime;
    private Instant planEndTime;
    private Instant actualStartTime;
    private Instant actualEndTime;
    private Status status;

    public enum Status {
        PENDING,
        PROCESSING,
        FINISHED,
    }

}
