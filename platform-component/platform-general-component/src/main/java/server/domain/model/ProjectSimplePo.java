package server.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 工程简要信息 PO
 *
 * @author 56807
 */
@Data
@TableName("TB_GC_GCXX")
public class ProjectSimplePo {

    @TableId("CID")
    private String id;

    @TableField("CGCMC")
    private String projectName;

    @TableField("CGCZZ")
    private String author;

    @TableField("CGCMS")
    private String projectDesc;

    @TableField("DCJSJ")
    private LocalDateTime createTime;
}
