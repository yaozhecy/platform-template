package com.cy.generate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.generate.domain.model.FieldMapItemPo;
import com.cy.generate.domain.vo.field.FieldMapItemVo;
import com.cy.generate.domain.vo.field.QueryParamVo;
import com.cy.generate.domain.vo.field.SourceFieldVo;
import com.cy.generate.domain.vo.field.TransverterInfoVo;

import java.util.List;

/**
 * 字段映射规则配置类
 *
 * @author 56807
 */
public interface IFieldMapService extends IService<FieldMapItemPo> {
    /**
     * 查询字段列表
     *
     * @return
     */
    List<SourceFieldVo> queryFieldList();

    /**
     * 原始字段列表查询接口
     *
     * @return 字段列表
     */
    List<SourceFieldVo> sourceFieldList();

    /**
     * 查询基础转换器
     *
     * @return 转换器列表
     */
    List<TransverterInfoVo> getBaseTransver();

    /**
     * 添加字段映射
     *
     * @param vo 参数
     * @return 主键
     */
    String add(FieldMapItemVo vo);

    /**
     * 删除记录
     *
     * @param id 记录ID
     */
    void delete(String id);

    /**
     * 分页查询
     *
     * @param current 当前页
     * @param size    每页行数
     * @param paramVo 查询参数
     * @return 查询结果
     */
    IPage<FieldMapItemVo> queryPage(Long current, Long size, QueryParamVo paramVo);
}
