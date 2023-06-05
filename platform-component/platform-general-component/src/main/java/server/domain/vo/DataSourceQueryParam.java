package server.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据源查询参数
 *
 * @author 56807
 */
@Data
@ApiModel(value = "数据源", subTypes = {DataSourceQueryParam.class})
public class DataSourceQueryParam {

    @ApiModelProperty("查询参数")
    private String query;
}
