package server.domain.vo;

import lombok.Data;

/**
 * 协作文档PO
 *
 * @author 56807
 */
@Data
public class CooperationDocVo {
    /**
     * ID
     */
    private String id;
    /**
     * 文档编码
     */
    private String docCode;
    /**
     * key
     */
    private String ckey;
    /**
     * 文档名称
     */
    private String docName;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 文件地址
     */
    private String docUrl;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 用户
     */
    private String account = "A1";
}
