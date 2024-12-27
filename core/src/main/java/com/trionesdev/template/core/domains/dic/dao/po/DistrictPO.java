package com.trionesdev.template.core.domains.dic.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import com.trionesdev.commons.mybatisplus.typehandlers.StringCollectionTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dic_district", autoResultMap = true)
public class DistrictPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String countryCode;
    /**
     * 编码
     */
    private String code;
    /**
     * 上级编码
     */
    private String parentCode;
    private String cityCode;
    /**
     * 地区级别
     */
    private Integer level;
    /**
     * 地区中文名称
     */
    private String name;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 所有上级地区邮政编码
     */
    @TableField(typeHandler = StringCollectionTypeHandler.class)
    private List<String> prevCodes;
    /**
     * 合并名称
     */
    private String mergeName;

}
