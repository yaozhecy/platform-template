package com.cy.generate.domain.transfor;

import com.cy.generate.domain.model.*;
import com.cy.generate.domain.vo.CooperationDocVo;
import com.cy.generate.domain.vo.field.FieldMapItemVo;
import com.cy.generate.domain.vo.project.item.ProjectSettingVo;
import com.cy.generate.domain.vo.project.item.ProjectSimpleVo;
import com.cy.generate.domain.vo.template.TemplateGroupVo;
import com.cy.generate.domain.vo.template.TemplateInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模板模型转换
 *
 * @author muruohan
 */
@Mapper
public interface CommonTransfor {
    CommonTransfor INSTANCE = Mappers.getMapper(CommonTransfor.class);

    /**
     * toPO
     *
     * @param vo VO
     * @return PO
     */
    TemplateGroupPo toPo(TemplateGroupVo vo);

    /**
     * toVo
     *
     * @param po PO
     * @return VO
     */
    TemplateGroupVo toVo(TemplateGroupPo po);

    /**
     * toPO
     *
     * @param vo VO
     * @return PO
     */
    TemplateInfoPo toPo(TemplateInfoVo vo);

    /**
     * toVo
     *
     * @param po PO
     * @return VO
     */
    TemplateInfoVo toVo(TemplateInfoPo po);

    /**
     * toVo
     *
     * @param po PO
     * @return VO
     */
    ProjectSimpleVo toVo(ProjectSimplePo po);

    /**
     * toPo
     *
     * @param vo vo
     * @return PO
     */
    ProjectSimplePo toPo(ProjectSimpleVo vo);

    /**
     * toPo
     *
     * @param vo vo
     * @return PO
     */
    FieldMapItemPo toPo(FieldMapItemVo vo);

    /**
     * toVo
     *
     * @param po PO
     * @return VO
     */
    FieldMapItemVo toVo(FieldMapItemPo po);

    /**
     * toVo
     *
     * @param po PO
     * @return VO
     */
    CooperationDocVo toVo(CooperationDocPo po);

    /**
     * toPo
     *
     * @param vo vo
     * @return PO
     */
    ProjectSettingPo toPo(ProjectSettingVo vo);
}