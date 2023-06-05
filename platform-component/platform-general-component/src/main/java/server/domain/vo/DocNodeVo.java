package server.domain.vo;

import lombok.Data;

/**
 * 树节点
 * @author chenyang
 */
@Data
public class DocNodeVo {
    /**
     * id
     */
    private String id;
    /**
     * 父级节点
     */
    private String superId;
    /**
     * 标签
     */
    private String label;
    /**
     * 节点类型：0 目录，1 叶节点
     */
    private Integer type;
}
