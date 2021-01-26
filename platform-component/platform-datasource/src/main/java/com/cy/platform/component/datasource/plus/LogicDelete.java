package com.cy.platform.component.datasource.plus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.cy.platform.component.datasource.constant.SqlMethodConstant;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 逻辑删除
 *
 * @author 56807
 */
public class LogicDelete extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        /* 执行 SQL ，动态 SQL 参考类 SqlMethod */
        String sql = "UPDATE " + tableInfo.getTableName() + " SET delete_flag = '1' where id = #{id}";
        /* mapper 接口方法名一致 */
        String method = SqlMethodConstant.LOGIC_DELETE;
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, method, sqlSource);
    }
}
