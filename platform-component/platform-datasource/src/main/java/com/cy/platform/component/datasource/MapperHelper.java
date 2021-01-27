package com.cy.platform.component.datasource;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.cy.platform.component.datasource.base.BaseEntity;
import com.cy.platform.component.datasource.constant.SqlMethodConstant;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MapperHelper
 *
 * @author 陈阳
 * @date 2020-02-21
 */
@Component
public class MapperHelper {
    /**
     * 根据参数查询是否存在
     */
    public <T extends BaseEntity<?>> boolean exist(Class<T> clz, QueryWrapper<T> wrapper) {
        SqlSession sqlSession = sqlSession(clz);
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put(Constants.WRAPPER, wrapper);
        boolean exist;
        try {
            Integer count = sqlSession.selectOne(sqlStatement(clz, SqlMethod.SELECT_COUNT), queryParam);
            exist = count != null && count > 0;
        } finally {
            closeSqlSession(sqlSession, clz);
        }
        return exist;
    }

    /**
     * 根据ID查询实体
     */
    public <T extends BaseEntity<?>> T query(Class<T> clz, Serializable id) {
        SqlSession sqlSession = sqlSession(clz);
        try {
            return sqlSession.selectOne(sqlStatement(clz, SqlMethod.SELECT_BY_ID), id);
        } finally {
            closeSqlSession(sqlSession, clz);
        }
    }

    /**
     * 根据参数查询
     */
    public <T extends BaseEntity<?>> T queryOne(Class<T> clz, QueryWrapper<T> wrapper) {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put(Constants.WRAPPER, wrapper);
        SqlSession sqlSession = sqlSession(clz);
        try {
            return sqlSession.selectOne(sqlStatement(clz, SqlMethod.SELECT_ONE), queryParam);
        } finally {
            closeSqlSession(sqlSession, clz);
        }
    }

    /**
     * 根据参数查询
     */
    public <T extends BaseEntity<?>> List<T> query(Class<T> clz, QueryWrapper<T> wrapper) {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put(Constants.WRAPPER, wrapper);
        SqlSession sqlSession = sqlSession(clz);
        try {
            List<T> result = sqlSession.selectList(sqlStatement(clz, SqlMethod.SELECT_LIST), queryParam);
            return result == null ? new ArrayList<>() : result;
        } finally {
            closeSqlSession(sqlSession, clz);
        }
    }

    /**
     * 根据参数查询
     */
    public <T extends BaseEntity<?>> List<T> selectByLamadaMap(Class<T> clz, Map<SFunction<T, ?>, String> param) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("delete_flag", "0").lambda().allEq(param);
        return query(clz, wrapper);
    }

    /**
     * 根据参数查询
     */
    public <T extends BaseEntity<?>> List<T> selectByMap(Class<T> clz, Map<String, Object> param) {
        SqlSession sqlSession = sqlSession(clz);
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put(Constants.COLUMN_MAP, param);
        try {
            return sqlSession.selectList(sqlStatement(clz, SqlMethod.SELECT_BY_MAP), queryParam);
        } finally {
            closeSqlSession(sqlSession, clz);
        }
    }

    /**
     * 插入（字段选择插入）
     */
    public <T extends BaseEntity<?>> boolean insert(T model) {
        Class<?> clz = model.getClass();
        SqlSession sqlSession = sqlSession(clz);
        try {
            return SqlHelper.retBool(sqlSession.insert(sqlStatement(clz, SqlMethod.INSERT_ONE), model));
        } finally {
            closeSqlSession(sqlSession, clz);
        }
    }

    public <T extends BaseEntity<?>> boolean update(T model, Wrapper<T> updateWrapper) {
        Class<?> clz = model.getClass();
        Map<String, Object> map = new HashMap<>(2);
        map.put(Constants.ENTITY, model);
        map.put(Constants.WRAPPER, updateWrapper);
        // update
        SqlSession sqlSession = sqlSession(clz);
        try {
            return SqlHelper.retBool(sqlSession.update(sqlStatement(clz, SqlMethod.UPDATE), map));
        } finally {
            closeSqlSession(sqlSession, clz);
        }
    }

    public <T extends BaseEntity<?>> boolean delete(Class<T> clz, Serializable id) {
        SqlSession sqlSession = sqlSession(clz);
        Map<String, Object> map = new HashMap<>(3);
        map.put("id", id);
        try {
            return SqlHelper.retBool(sqlSession.update(sqlStatement(clz, SqlMethodConstant.LOGIC_DELETE), map));
        } finally {
            closeSqlSession(sqlSession, clz);
        }
    }

    public <T extends BaseEntity<?>> boolean delete(T entity) {
        return delete(entity.getClass(), entity.getId());
    }

    /**
     * 获取SqlStatement
     *
     * @param sqlMethod sqlMethod
     */
    private String sqlStatement(Class<?> clz, SqlMethod sqlMethod) {
        return sqlStatement(clz, sqlMethod.getMethod());
    }

    /**
     * 获取SqlStatement
     *
     * @param sqlMethod sqlMethod
     */
    private String sqlStatement(Class<?> clz, String sqlMethod) {
        return SqlHelper.table(clz).getSqlStatement(sqlMethod);
    }

    /**
     * 获取Session 默认自动提交
     */
    private SqlSession sqlSession(Class<?> clz) {
        return SqlHelper.sqlSession(clz);
    }

    /**
     * 释放sqlSession
     *
     * @param sqlSession session
     */
    private void closeSqlSession(SqlSession sqlSession, Class<?> clz) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(clz));
    }
}
