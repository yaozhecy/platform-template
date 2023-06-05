package server.core.transverter;

import lombok.Getter;

/**
 * 基础转换器
 *
 * @author 56807
 */
@Getter
public enum BaseTransverterEnum {
    /**
     * 空转换器
     */
    EMPTY_Transverter("empty", "空转换器"),
    /**
     * 驼峰转换器
     */
    HUMP_Transverter("hump", "驼峰转换器"),
    /**
     * 首字母大写
     */
    CAPITALIZE_Transverter("capitalize", "首字母大写"),
    /**
     * 首字母小写
     */
    CAPTURE_Transverter("capture", "首字母小写");

    private final String code;
    private final String name;

    BaseTransverterEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取转换器
     *
     * @param code 编码
     * @return 转换器
     */
    public static BaseTransverterEnum getTransverter(String code) {
        for (BaseTransverterEnum value : BaseTransverterEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return EMPTY_Transverter;
    }
}
