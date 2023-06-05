package server.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 数据源
 *
 * @author 56807
 */
@Data
@TableName("TB_MB_MBFZ")
public class TemplateGroupPo {
    /**
     * CID
     */
    @TableId("CID")
    private String id;
    /**
     * 分组名称
     */
    @TableField("CFZMC")
    private String groupName;
    /**
     * 是否默认
     */
    @TableField("ISFMR")
    private Integer type;
}
