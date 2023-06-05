package server.domain.vo.project;

import com.alibaba.fastjson.JSONObject;
import com.cy.platform.generation.server.domain.vo.project.item.ProjectSettingVo;
import com.cy.platform.generation.server.domain.vo.project.item.ProjectSimpleVo;
import lombok.Data;

import java.util.List;

/**
 * 工程完整信息
 *
 * @author 56807
 */
@Data
public class ProjectInfoVo {
    /**
     * 基本信息
     */
    private ProjectSimpleVo simpleInfo;
    /**
     * 包信息
     */
    private ProjectSettingVo settingInfo;
    /**
     * 关联数据源ID
     */
    private List<JSONObject> datasource;
    /**
     * 关联模版ID
     */
    private List<JSONObject> templates;
}
