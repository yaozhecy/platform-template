package server.domain.vo.field;

import lombok.Data;

/**
 * 转换器信息
 *
 * @author 56807
 */
@Data
public class TransverterInfoVo {
    /**
     * 转换器编码
     */
    private String code;
    /**
     * 转换器名称
     */
    private String name;

    public TransverterInfoVo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TransverterInfoVo build(String code, String name) {
        return new TransverterInfoVo(code, name);
    }
}
