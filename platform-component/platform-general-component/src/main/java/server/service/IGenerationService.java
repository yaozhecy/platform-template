package server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.platform.generation.server.domain.model.DataSourcePo;
import com.cy.platform.generation.server.domain.vo.CodeInfoVo;
import com.cy.platform.generation.server.domain.vo.CodeParamsVo;
import com.cy.platform.generation.server.domain.vo.code.CodeListVo;

/**
 * 代码生成服务
 *
 * @author 56807
 */
public interface IGenerationService extends IService<DataSourcePo> {
    /**
     * 根据数据源查询元数据
     *
     * @param ds 数据源
     */
    void generation(String ds);

    /**
     * 生成代码
     *
     * @param dsId    数据源ID
     * @param tableId 表
     * @return 结果
     */
    CodeInfoVo generationCode(String dsId, String tableId);

    /**
     * 通过模板生成代码
     *
     * @param templateGroupId 模板组ID
     * @param dsId            数据源
     * @param tableId         表
     * @return 结果
     */
    CodeListVo generationCode(String templateGroupId, String dsId, String tableId);

    /**
     * 导出压缩文件
     *
     * @param params 参数
     * @return 文件
     */
    byte[] exportZip(CodeParamsVo params);
}
