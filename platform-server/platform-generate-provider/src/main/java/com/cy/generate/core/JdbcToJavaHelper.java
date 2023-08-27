package com.cy.generate.core;

import java.util.HashMap;
import java.util.Map;

/**
 * JDBC类型转JAVA类型
 *
 * @author 56807
 */
public class JdbcToJavaHelper {
    private static final Map<String, String> SOURCE = new HashMap<>();

    static {
        SOURCE.put("nvarchar", "String");
        SOURCE.put("varchar", "String");
        SOURCE.put("int", "Integer");
        SOURCE.put("Decimal", "BigDecimal");
        SOURCE.put("datetime", "Date");
        SOURCE.put("datetime2", "Date");
    }

    public static String convertToClassName(String value) {
        String[] values = value.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < values.length; i++) {
            sb.append(captureName(values[i]));
        }
        return sb.toString();
    }

    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static String convertToJava(String value) {
        return SOURCE.getOrDefault(value, "String");
    }

    /**
     * 首字母小写
     *
     * @param name 名称
     * @return 字符串
     */
    public static String smallName(String name) {
        char[] cs = name.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);
    }
}
