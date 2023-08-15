package com.cy.platform.model.db;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.cy.platform.common.collect.CollectTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionHolder;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
@Component
public class MapperHelper {

    private final int batchSize = 1000;

    public <T> int updateBatch(List<T> entities) {
        if (CollectTools.isEmpty(entities)) {
            return 0;
        }

        Map<Class<?>, List<T>> map = CollectTools.groupBy(entities, T::getClass);
        for (Map.Entry<Class<?>, List<T>> entry : map.entrySet()) {
            String sqlStatement = sqlStatement(entry.getClass(), SqlMethod.UPDATE_BY_ID);
            this.executeBatch(entry.getClass(), entry.getValue(), (sqlSession, entity) -> {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put("et", entity);
                sqlSession.update(sqlStatement, param);
            });
        }

        return 0;
    }

    private String sqlStatement(Class<?> entityClass, SqlMethod sqlMethod) {
        return SqlHelper.table(entityClass).getCurrentNamespace() + StringPool.DOT + sqlMethod.getMethod();
    }

    private SqlSession getSqlSession(Class<?> entityClass) {
        return SqlHelper.sqlSession(entityClass);
    }

    private void closeSqlSession(SqlSession session, Class<?> entityClass) {
        SqlSessionUtils.closeSqlSession(session, GlobalConfigUtils.currentSessionFactory(entityClass));
    }

    private <T> void executeBatch(Class<?> entityClass, List<T> list, BiConsumer<SqlSession, T> consumer) {
        if (!CollectionUtils.isEmpty(list)) {
            executeBatch(entityClass, sqlSession -> {
                int size = list.size();
                int i = 1;
                for (T element : list) {
                    consumer.accept(sqlSession, element);
                    if ((i % batchSize == 0) || i == size) {
                        sqlSession.flushStatements();
                    }
                    i++;
                }
            });
        }
    }

    private static void executeBatch(Class<?> entityClass, Consumer<SqlSession> consumer) {
        SqlSessionFactory sqlSessionFactory = GlobalConfigUtils.currentSessionFactory(entityClass);
        SqlSessionHolder sqlSessionHolder = (SqlSessionHolder) TransactionSynchronizationManager.getResource(sqlSessionFactory);
        boolean transaction = TransactionSynchronizationManager.isSynchronizationActive();
        if (sqlSessionHolder != null) {
            SqlSession sqlSession = sqlSessionHolder.getSqlSession();
            //原生无法支持执行器切换，当存在批量操作时，会嵌套两个session的，优先commit上一个session
            //按道理来说，这里的值应该一直为false。
            sqlSession.commit(!transaction);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        if (!transaction) {
            log.warn("SqlSession [" + sqlSession + "] Transaction not enabled");
        }
        try {
            consumer.accept(sqlSession);
            //非事物情况下，强制commit。
            sqlSession.commit(!transaction);
        } catch (Throwable t) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
