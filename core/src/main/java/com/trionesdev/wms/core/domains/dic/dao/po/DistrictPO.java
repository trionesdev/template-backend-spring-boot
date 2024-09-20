package com.trionesdev.wms.core.domains.dic.dao.po;

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
@TableName(value = "dic_district")
public class DistrictPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String countryCode;
    /**
     * 地区邮政编码
     */
    private String code;
    /**
     * 上级地区邮政编码
     */
    private String parentCode;
    /**
     * 地区级别
     */
    private Integer level;
    /**
     * 地区中文名称
     */
    private String nameZh;
    /**
     * 地区英文名称(中国地名则为拼音)
     */
    private String nameEn;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 所有上级地区邮政编码
     */
    @TableField(typeHandler = StringCollectionTypeHandler.class)
    private List<String> prevCodes;
    /**
     * 合并名称
     */
    private String mergeNameZh;

}
