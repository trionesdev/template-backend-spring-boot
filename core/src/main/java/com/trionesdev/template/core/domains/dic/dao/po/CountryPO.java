package com.trionesdev.template.core.domains.dic.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dic_country")
public class CountryPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 国际代码
     */
    private String code;
    /**
     * 英文缩写
     */
    private String abbr;
    /**
     * 中文名称
     */
    private String name;
    /**
     * 英文名称
     */
    private String nameEn;
}
