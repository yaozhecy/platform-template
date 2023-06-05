package server.domain.vo.field;

import lombok.Data;

/**
 * 查询参数
 *
 * @author 56807
 */
@Data
public class QueryParamVo {
    /**
     * 主题
     */
    private String tableTheme;
    /**
     * 关键字
     */
    private String keyWord;
}
