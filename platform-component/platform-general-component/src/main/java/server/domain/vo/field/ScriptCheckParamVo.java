package server.domain.vo.field;

import lombok.Data;

/**
 * 脚本检查参数
 *
 * @author 56807
 */
@Data
public class ScriptCheckParamVo {
    /**
     * 脚本
     */
    private String script;
    /**
     * 值
     */
    private String value;
}
