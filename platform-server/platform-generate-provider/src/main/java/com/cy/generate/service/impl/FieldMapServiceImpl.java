package com.cy.generate.service.impl;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.generate.anno.FieldDesc;
import com.cy.generate.common.ServiceAssert;
import com.cy.generate.core.model.TableInfo;
import com.cy.generate.core.transverter.BaseTransverterEnum;
import com.cy.generate.domain.mapper.FieldMapItemMapper;
import com.cy.generate.domain.model.FieldMapItemPo;
import com.cy.generate.domain.transfor.CommonTransfor;
import com.cy.generate.domain.vo.CommonDataVo;
import com.cy.generate.domain.vo.field.FieldMapItemVo;
import com.cy.generate.domain.vo.field.QueryParamVo;
import com.cy.generate.domain.vo.field.SourceFieldVo;
import com.cy.generate.domain.vo.field.TransverterInfoVo;
import com.cy.generate.service.IFieldMapService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字段映射规则服务
 *
 * @author 56807
 */
@Service
public class FieldMapServiceImpl extends ServiceImpl<FieldMapItemMapper, FieldMapItemPo>
        implements IFieldMapService {
    private final Map<String, SourceFieldVo> fieldMap = new TreeMap<>();

    public FieldMapServiceImpl() {
        //step 1.反射获取表字段
        Field[] tableFields = ReflectUtil.getFields(TableInfo.class);
        for (Field field : tableFields) {
            FieldDesc fieldDesc = field.getAnnotation(FieldDesc.class);
            if (fieldDesc != null) {
                fieldMap.put(field.getName(), SourceFieldVo.build(field.getName(),
                        fieldDesc.value(), FieldMapItemVo.FIELD_LEVEL_TABLE));
            }
        }
        //step 2.反射获取列表数据
        Field[] columns = ReflectUtil.getFields(CommonDataVo.class);
        for (Field field : columns) {
            FieldDesc fieldDesc = field.getAnnotation(FieldDesc.class);
            if (fieldDesc != null) {
                fieldMap.put(field.getName(), SourceFieldVo.build(field.getName(),
                        fieldDesc.value(), FieldMapItemVo.FIELD_LEVEL_COLUMN));
            }
        }
    }

    @Override
    public List<SourceFieldVo> queryFieldList() {
        return null;
    }

    @Override
    public List<SourceFieldVo> sourceFieldList() {
        return new ArrayList<>(fieldMap.values());
    }

    @Override
    public List<TransverterInfoVo> getBaseTransver() {
        BaseTransverterEnum[] transverters = BaseTransverterEnum.values();
        return Arrays.stream(transverters).map(n -> TransverterInfoVo.build(n.getCode(),
                n.getName())).collect(Collectors.toList());
    }

    @Override
    public String add(FieldMapItemVo vo) {
        //step 1.校验数据
        SourceFieldVo fieldVo = fieldMap.get(vo.getOrgFieldCode());
        ServiceAssert.notNull(fieldVo, "未查询到原节点数据！！！");
        Long count = baseMapper.selectCount(new LambdaQueryWrapper<FieldMapItemPo>()
                .eq(FieldMapItemPo::getFieldCode, vo.getFieldCode()));
        ServiceAssert.isTure(count != null && count == 0, "字段已使用，请重新输入");

        //step 2.保存字段数据
        FieldMapItemPo po = CommonTransfor.INSTANCE.toPo(vo);
        po.setTableTheme("user-defined");
        po.setFieldLevel(fieldVo.getLevel());
        po.setCreateTime(new Date());
        baseMapper.insert(po);
        return po.getId();
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public IPage<FieldMapItemVo> queryPage(Long current, Long size, QueryParamVo paramVo) {
        LambdaQueryWrapper<FieldMapItemPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FieldMapItemPo::getTableTheme, paramVo.getTableTheme());
        if (StringUtils.isNotBlank(paramVo.getKeyWord())) {
            queryWrapper.nested(n ->
                    n.eq(FieldMapItemPo::getFieldCode, paramVo.getKeyWord())
                            .or()
                            .like(FieldMapItemPo::getFieldName, paramVo.getKeyWord()));
        }
        queryWrapper.orderByDesc(FieldMapItemPo::getCreateTime);

        return baseMapper.selectPage(new Page<>(current, size), queryWrapper)
                .convert(CommonTransfor.INSTANCE::toVo);
    }
}
