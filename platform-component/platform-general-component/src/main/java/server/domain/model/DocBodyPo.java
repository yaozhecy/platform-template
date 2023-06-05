package server.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文档内容地址
 *
 * @author 56807
 */
@Data
@TableName("TB_WD_WDSJ")
public class DocBodyPo {
    /**
     * 主键
     */
    @TableId("CID")
    private String id;
    /**
     * 目录ID
     */
    @TableField("CMLID")
    private String nodeId;
    /**
     * 目录名称
     */
    @TableField("CMLMC")
    private String nodeName;
    /**
     * 文件地址
     */
    @TableField("CWJDZ")
    private String bodyPath;
    /**
     * 数据状态
     */
    @TableField("ISJZT")
    private Integer status;
}
