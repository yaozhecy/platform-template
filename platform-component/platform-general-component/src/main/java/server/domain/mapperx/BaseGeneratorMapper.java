package server.domain.mapperx;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 基础代码生成器
 *
 * @author chenyang
 * @since 2021-09-18
 */
@Mapper
public interface BaseGeneratorMapper {
    /**
     * 查询表列表信息
     *
     * @param map 表列表信息
     * @return 表列表
     */
    @MapKey("id")
    List<Map<String, Object>> queryList(Map<String, Object> map);

    /**
     * 查询表信息
     *
     * @param tableName 表名
     * @return 表信息
     */
    @MapKey("id")
    Map<String, String> queryTable(String tableName);

    /**
     * 查询行信息
     *
     * @param tableName 表名
     * @return 行信息
     */
    @MapKey("id")
    List<Map<String, String>> queryColumns(String tableName);
}
