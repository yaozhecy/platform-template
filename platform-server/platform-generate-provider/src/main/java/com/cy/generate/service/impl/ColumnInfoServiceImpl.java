package com.cy.generate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.generate.domain.mapper.ColumnInfoMapper;
import com.cy.generate.domain.model.ColumnInfoPo;
import com.cy.generate.domain.transfor.DataSourceTransfor;
import com.cy.generate.domain.vo.ColumnInfoVo;
import com.cy.generate.domain.vo.ColumnListVo;
import com.cy.generate.service.IColumnInfoService;
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
public class ColumnInfoServiceImpl extends ServiceImpl<ColumnInfoMapper, ColumnInfoPo>
        implements IColumnInfoService {

    @Override
    public List<ColumnInfoVo> queryColumnList(String id) {
        List<ColumnInfoPo> infoPos = baseMapper.selectList(new LambdaQueryWrapper<ColumnInfoPo>()
                .eq(ColumnInfoPo::getTableId, id).notIn(ColumnInfoPo::getColumnName, "CID", "DCJSJ"));
        return infoPos.stream().map(DataSourceTransfor.INSTANCE::toVo).collect(Collectors.toList());
    }

    @Override
    public ColumnInfoVo queryColumnInfo(String id) {
        return DataSourceTransfor.INSTANCE.toVo(baseMapper.selectById(id));
    }

    @Override
    public void updateColumnInfo(ColumnListVo infoList) {

    }

    @Override
    public void generateColumnInfo(String ds, List<ColumnInfoPo> columnInfoPos) {
        //状态修改为临时状态
        baseMapper.updateToTemp(ds);

        //校验库表是否已经保持
        Set<String> set = columnInfoPos.stream().map(ColumnInfoPo::getColumnName).collect(Collectors.toSet());
        if (set.isEmpty()) {
            throw new RuntimeException();
        }
        List<ColumnInfoPo> tableList = baseMapper.selectList(new LambdaQueryWrapper<ColumnInfoPo>()
                .eq(ColumnInfoPo::getDsId, ds).in(ColumnInfoPo::getColumnName, set));
        Map<String, ColumnInfoPo> map = tableList.stream().collect(Collectors.toMap(
                ColumnInfoPo::getColumnName, n -> n, (n1, n2) -> n2));

        List<ColumnInfoPo> updateList = new ArrayList<>();
        List<ColumnInfoPo> addList = new ArrayList<>();
        for (ColumnInfoPo infoPo : columnInfoPos) {
            ColumnInfoPo temp = map.get(infoPo.getColumnName());
            if (temp != null) {
                temp.setColumnType(infoPo.getColumnType());
                temp.setColumnComment(infoPo.getColumnComment());
                temp.setStatus(0);
                updateList.add(temp);
            } else {
                addList.add(infoPo);
            }
        }
        //批量保存
        saveBatch(addList);
        updateBatchById(updateList);
        //删除临时状态数据
        baseMapper.deleteTemp(ds);
    }
}
