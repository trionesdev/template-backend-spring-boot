package com.trionesdev.wms.core.domains.dic.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dic_dictionary")
public class DictionaryPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String parentId;
    private String typeCode;
    private String name;
    private String code;
}
