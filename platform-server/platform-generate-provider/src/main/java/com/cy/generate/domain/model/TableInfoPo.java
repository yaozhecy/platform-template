package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.generate.common.ServiceException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 数据源
 *
 * @author 56807
 */
@Data
@TableName("TB_DB_KBXX")
public class TableInfoPo {
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
     * 序号
     */
    @TableField("ISORT")
    private Integer tbSort;
    /**
     * 中文名称
     */
    @TableField("CZWMC")
    private String chineseName;
    /**
     * 库表名称
     */
    @TableField("CKBMC")
    private String tableName;
    /**
     * 库表描述
     */
    @TableField("CKBMS")
    private String tableDesc;
    /**
     * 实体名称
     */
    @TableField("CSTMC")
    private String entityName;
    /**
     * 英文名称
     */
    @TableField("CYWMC")
    private String englishName;
    /**
     * 创建时间
     */
    @TableField("CCJSJ")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("CXGSJ")
    private Date updateTime;

    @TableField("ISJZT")
    private Integer status = 0;

    public void valid() {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isBlank(tableName)) {
            builder.append("库表名称不能为空");
        }
        if (StringUtils.isBlank(entityName)) {
            builder.append("实体名称不能为空");
        }
        if (StringUtils.isBlank(englishName)) {
            builder.append("英文名称不能为空");
        }
        String msg = builder.toString();
        if (!msg.isEmpty()) {
            throw new ServiceException(msg);
        }
    }
}
