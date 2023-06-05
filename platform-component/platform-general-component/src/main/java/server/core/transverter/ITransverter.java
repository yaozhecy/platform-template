package server.core.transverter;

/**
 * 字段转换器
 *
 * @author 56807
 */
public interface ITransverter {
    /**
     * 数据转换
     *
     * @param source 原数据
     * @return 转换后数据
     */
    String transition(String source);
}
