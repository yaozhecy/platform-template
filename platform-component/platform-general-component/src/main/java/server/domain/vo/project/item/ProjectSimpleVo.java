package server.domain.vo.project.item;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 工程简要信息
 *
 * @author 56807
 */
@Data
public class ProjectSimpleVo {
    /**
     * 工程ID
     */
    private String id;
    /**
     * 工程名称
     */
    private String projectName;
    /**
     * 作者
     */
    private String author;
    /**
     * 工程描述
     */
    private String projectDesc;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
