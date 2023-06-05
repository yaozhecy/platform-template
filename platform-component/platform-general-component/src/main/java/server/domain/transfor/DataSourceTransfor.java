package server.domain.transfor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import server.domain.model.*;
import server.domain.vo.*;

/**
 * @author muruohan
 */
@Mapper
public interface DataSourceTransfor {
    DataSourceTransfor INSTANCE = Mappers.getMapper(DataSourceTransfor.class);

    /**
     * toPo
     *
     * @param vo vo
     * @return po
     */
    DataSourceVo toVo(DataSourcePo vo);

    /**
     * toPo
     *
     * @param vo vo
     * @return po
     */
    ColumnInfoVo toVo(ColumnInfoPo vo);

    /**
     * toVo
     *
     * @param po po
     * @return vo
     */
    TableInfoVo toVo(TableInfoPo po);

    /**
     * PO
     *
     * @param vo VO
     * @return PO
     */
    DataSourcePo toPo(DataSourceVo vo);

    /**
     * PO
     *
     * @param vo VO
     * @return PO
     */
    TableInfoPo toPo(TableInfoVo vo);

    /**
     * PO
     *
     * @param vo VO
     * @return PO
     */
    CommonDataPo toPo(CommonDataVo vo);

    /**
     * toVo
     * @param po Po
     * @return Vo
     */
    DocNodeVo toVo(DocNodePo po);

    /**
     * toPo
     * @param vo Vo
     * @return Po
     */
    DocNodePo toPo(DocNodeVo vo);
}