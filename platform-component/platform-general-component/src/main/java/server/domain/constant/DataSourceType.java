package server.domain.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 数据源类型
 *
 * @author 56807
 */
@Getter
public enum DataSourceType implements IEnum<Integer> {
    /**
     * 基于文本
     */
    Excel(0, ""),
    /**
     * MYSQL
     */
    MYSQL(1, "com.mysql.cj.jdbc.Driver"),
    /**
     * SQLSERVER
     */
    SQLSERVER(2, "com.microsoft.sqlserver.jdbc.SQLServerDriver");

    @EnumValue
    @JsonValue
    private final int code;
    private final String classDriverName;

    DataSourceType(int code, String classDriverName) {
        this.code = code;
        this.classDriverName = classDriverName;
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
