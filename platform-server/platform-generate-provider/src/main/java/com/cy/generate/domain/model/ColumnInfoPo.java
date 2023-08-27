package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.generate.common.ServiceException;
import com.cy.generate.core.constant.DbTypeConstant;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


/**
 * 列表信息
 *
 * @author 56807
 */
@Data
@TableName("TB_DB_ZDXX")
public class ColumnInfoPo {
    /**
     * CID
     */
    @TableId("CID")
    private String id;
    /**
     * 数据源ID
     */
    @TableField("CSJYID")
    private String dsId;
    /**
     * 库表ID
     */
    @TableField("CKBID")
    private String tableId;
    /**
     * 序号
     */
    @TableField("ISORT")
    private Integer colSort;
    /**
     * 是否主键
     */
    @TableField("IZJ")
    private Integer columnKey;
    /**
     * 字段名称
     */
    @TableField("CZDMC")
    private String columnName;
    /**
     * 字段类型
     */
    @TableField("CZDLX")
    private String columnType;
    /**
     * 是否必传
     */
    @TableField("ISFBC")
    private Integer required;
    /**
     * 数据长度
     */
    @TableField("CSJCD")
    private String length;
    /**
     * 中文含义
     */
    @TableField("CZWHY")
    private String chineseName;
    /**
     * 英文名称
     */
    @TableField("CYWMC")
    private String englishName;
    /**
     * 字段描述
     */
    @TableField("CZDMS")
    private String columnComment;
    /**
     * 属性名称
     */
    @TableField("CSXMC")
    private String propertyName;
    /**
     * 数据类型
     */
    @TableField("CSJLX")
    private String dataType;
    /**
     * 数据状态
     */
    @TableField("ISJZT")
    private Integer status;

    public void valid() {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isBlank(columnName)) {
            builder.append("字段名称不能为空");
        }
        if (StringUtils.isBlank(columnType)) {
            builder.append("字段类型不能为空");
        } else {
            if (DbTypeConstant.NVARCHAR.equals(columnType) && !NumberUtils.isNumber(length)) {
                builder.append("字段错误");
            }
        }
        if (StringUtils.isBlank(chineseName)) {
            builder.append("中文名称不能为空");
        }
        if (StringUtils.isBlank(englishName)) {
            builder.append("英文名称不能为空");
        }
        String msg = builder.toString();
        if (!msg.isEmpty()) {
            throw new ServiceException(columnName + ":" + msg);
        }
    }
}
