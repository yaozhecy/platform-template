package com.cy.platform.common.collect;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Lambda 工具类
 *
 * @author 56807
 */
public final class LambdaTools {

    /**
     * 提取列表中字段，生成新列表
     *
     * @param source 列表数据
     * @param mapper 映射关系
     * @param <R>    R
     * @param <U>    U
     * @return 队列
     */
    public static <R, U> List<R> extractList(Collection<U> source, Function<? super U, ? extends R> mapper) {
        if (CollectTools.isEmpty(source)) {
            return new ArrayList<>();
        }
        return source.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 提取列表中字段，生成新列表
     *
     * @param source 列表数据
     * @param mapper 映射关系
     * @param filter 过滤器
     * @param <R>    R
     * @param <U>    U
     * @return 队列
     */
    public static <R, U> List<R> extractList(Collection<U> source, Function<? super U, ? extends R> mapper,
        Predicate<? super U> filter) {
        if (CollectTools.isEmpty(source)) {
            return new ArrayList<>();
        }
        return source.stream().filter(filter).map(mapper).collect(Collectors.toList());
    }

    /**
     * 提取列表中字段，生成新列表
     *
     * @param source 列表数据
     * @param mapper 映射关系
     * @param <R>    R
     * @param <U>    U
     * @return 队列
     */
    public static <R, U> Set<R> extractSet(Collection<U> source, Function<? super U, ? extends R> mapper) {
        if (CollectTools.isEmpty(source)) {
            return new HashSet<>();
        }
        return source.stream().map(mapper).collect(Collectors.toSet());
    }

    /**
     * list转Map
     *
     * @param collect 集合
     * @param mapper  映射关系
     * @param <R>     R
     * @param <U>     U
     * @return Map
     */
    public static <R, U> Map<R, List<U>> extractMap(Collection<U> collect, Function<? super U, ? extends R> mapper) {
        if (CollectTools.isEmpty(collect)) {
            return CollectTools.newHashMap();
        }
        return collect.stream().collect(Collectors.groupingBy(mapper));
    }

    /**
     * list转Map
     *
     * @param collect 集合
     * @param mapper  映射关系
     * @param <R>     R
     * @param <U>     U
     * @return Map
     */
    public static <R, U> Map<R, U> listToMap(Collection<U> collect, Function<? super U, ? extends R> mapper) {
        if (CollectTools.isEmpty(collect)) {
            return CollectTools.newHashMap();
        }
        return collect.stream().collect(Collectors.toMap(mapper, n -> n, (n1, n2) -> n2));
    }

    private LambdaTools() {
        throw new UnsupportedOperationException();
    }
}
