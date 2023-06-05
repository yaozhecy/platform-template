package server.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CommonDataPo
 *
 * @author 56807
 */
@Data
@TableName("TB_DB_MBPZ")
public class CommonDataPo {
    /**
     * CID
     */
    @TableId("CID")
    private String id;
    /**
     * 数据源ID
     */
    @TableField("CDSID")
    private String dataSourceId;
    /**
     * 工具包路径
     */
    @TableField("CGJBM")
    private String commonPackage;
    /**
     * 包路径
     */
    @TableField("CBM")
    private String packagePath;
    /**
     * 模块路径
     */
    @TableField("CMKMC")
    private String moduleName;
}
