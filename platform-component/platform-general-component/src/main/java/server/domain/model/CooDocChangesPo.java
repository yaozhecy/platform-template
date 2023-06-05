package server.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 协作文档相关历史
 *
 * @author 56807
 */
@Data
@TableName("TB_KZ_XZLS")
public class CooDocChangesPo {
    /**
     * 主键
     */
    @TableId("CID")
    private String id;

    /**
     * 文档编码
     */
    @TableField("CWDBM")
    private String docCode;

    /**
     * Key
     */
    @TableField("CKEY")
    private String docKey;

    /**
     * 文档版本
     */
    @TableField("CWDBB")
    private String version;

    /**
     * 用户ID
     */
    @TableField("CYHID")
    private String userId;

    /**
     * 用户名称
     */
    @TableField("CYHMC")
    private String userName;

    /**
     * 文档编码
     */
    @TableField("CCJSJ")
    private String created;
}
