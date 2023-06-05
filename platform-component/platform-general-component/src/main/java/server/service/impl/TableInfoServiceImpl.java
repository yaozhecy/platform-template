package server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.platform.generation.server.common.ServiceAssert;
import com.cy.platform.generation.server.core.model.TableInfo;
import com.cy.platform.generation.server.domain.mapper.TableInfoMapper;
import com.cy.platform.generation.server.domain.model.TableInfoPo;
import com.cy.platform.generation.server.domain.transfor.DataSourceTransfor;
import com.cy.platform.generation.server.domain.vo.TableInfoVo;
import com.cy.platform.generation.server.domain.vo.TableListVo;
import com.cy.platform.generation.server.domain.vo.table.TableParamVo;
import com.cy.platform.generation.server.service.ITableInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据源服务
 *
 * @author 56807
 */
@Service
public class TableInfoServiceImpl extends ServiceImpl<TableInfoMapper, TableInfoPo>
    implements ITableInfoService {

    @Override
    public IPage<TableInfoVo> queryTablePage(int current, int size, TableParamVo param) {
        return baseMapper.selectPage(new Page<>(current, size),
                new LambdaQueryWrapper<TableInfoPo>().eq(TableInfoPo::getDsId, param.getId()))
            .convert(DataSourceTransfor.INSTANCE::toVo);
    }

    @Override
    public List<TableInfoVo> queryTableList(String sourceId) {
        return baseMapper.selectList(new LambdaQueryWrapper<TableInfoPo>()
                .eq(TableInfoPo::getDsId, sourceId)).stream()
            .map(DataSourceTransfor.INSTANCE::toVo).collect(Collectors.toList());
    }


    @Override
    public TableInfoVo queryTableInfo(String id) {
        return DataSourceTransfor.INSTANCE.toVo(baseMapper.selectById(id));
    }

    @Override
    public void updateTableInfo(TableListVo list) {
        List<TableInfoPo> poList = list.getList().stream().map(DataSourceTransfor.INSTANCE::toPo)
            .collect(Collectors.toList());
        updateBatchById(poList);
    }

    @Override
    public void generateTableInfo(String ds, List<TableInfoPo> tableInfoPos) {
        //状态修改为临时状态
        baseMapper.updateToTemp(ds);

        //校验库表是否已经保持
        Set<String> tableSet = tableInfoPos.stream().map(TableInfoPo::getTableName)
            .collect(Collectors.toSet());
        if (tableSet.isEmpty()) {
            throw new RuntimeException();
        }
        List<TableInfoPo> tableList = baseMapper.selectList(new LambdaQueryWrapper<TableInfoPo>()
            .eq(TableInfoPo::getDsId, ds).in(TableInfoPo::getTableName, tableSet));
        Map<String, TableInfoPo> tableMap = tableList.stream().collect(Collectors.toMap(TableInfoPo::getTableName,
            n -> n, (n1, n2) -> n2));
        List<TableInfoPo> updateList = new ArrayList<>();
        List<TableInfoPo> addList = new ArrayList<>();
        for (TableInfoPo tableInfoPo : tableInfoPos) {
            TableInfoPo temp = tableMap.get(tableInfoPo.getTableName());
            if (temp != null) {
                temp.setTableDesc(tableInfoPo.getTableDesc());
                temp.setStatus(0);
                updateList.add(temp);
            } else {
                addList.add(tableInfoPo);
            }
        }
        //批量保存
        saveBatch(addList);
        updateBatchById(updateList);
        //删除临时状态数据
        baseMapper.deleteTemp(ds);
    }

    @Override
    public TableInfo queryGenTableInfo(String id) {
        TableInfo tableInfo = baseMapper.queryTableInfo(id);
        ServiceAssert.notNull(tableInfo, "查询失败，表不存在");

        tableInfo.getColumns().forEach(n -> {
            if (n.getRequired() != null && n.getRequired() == 1) {
                n.setNotNull("NOT");
            }
        });
        return tableInfo;
    }
}
