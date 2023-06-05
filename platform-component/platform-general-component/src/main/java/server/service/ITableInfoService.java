package server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.platform.generation.server.core.model.TableInfo;
import com.cy.platform.generation.server.domain.model.TableInfoPo;
import com.cy.platform.generation.server.domain.vo.TableInfoVo;
import com.cy.platform.generation.server.domain.vo.TableListVo;
import com.cy.platform.generation.server.domain.vo.table.TableParamVo;

import java.util.List;

/**
 * 库表信息服务
 *
 * @author 56807
 */
public interface ITableInfoService extends IService<TableInfoPo> {
    /**
     * 库表列表
     *
     * @param current 当前页
     * @param size    每页大小
     * @param param   请求参数
     * @return 库表列表
     */
    IPage<TableInfoVo> queryTablePage(int current, int size, TableParamVo param);

    /**
     * 库表列表
     *
     * @param sourceId 数据源ID
     * @return 库表列表
     */
    List<TableInfoVo> queryTableList(String sourceId);

    /**
     * 查询库表信息
     *
     * @param id 库表ID
     * @return 库表信息
     */
    TableInfoVo queryTableInfo(String id);

    /**
     * 更新库表信息
     *
     * @param list 库表信息列表
     */
    void updateTableInfo(TableListVo list);

    /**
     * 生成库表数据
     *
     * @param ds           数据源ID
     * @param tableInfoPos 库表数据
     */
    void generateTableInfo(String ds, List<TableInfoPo> tableInfoPos);

    /**
     * 查询代码生成数据
     *
     * @param id 库ID
     * @return 查询结果
     */
    TableInfo queryGenTableInfo(String id);
}
