package server.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 56807
 */
@Data
@TableName("TB_EX_KBXX")
public class ExcelTablePo {
    @TableId("CID")
    private String id;

    @TableField("CZID")
    private String zid;

    @TableField("CKBMC")
    private String tableName;

    @TableField("CKBBM")
    private String tableCode;

    @TableField("CYWMC")
    private String englishName;

    @TableField("CBZXX")
    private String comment;
}
