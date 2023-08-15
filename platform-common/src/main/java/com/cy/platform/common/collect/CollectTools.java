package com.cy.platform.common.collect;

import com.cy.platform.common.exception.CommonException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合工具类
 *
 * @author 56807
 */
public final class CollectTools {
    /**
     * 两个最大幂
     */
    public static final int MAX_POWER_OF_TWO = 1 << (Integer.SIZE - 2);

    /**
     * 校验集合是否为空
     *
     * @param array 数组
     * @param <T>   T
     * @return 空：true，非空：false
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 校验集合是否为空
     *
     * @param collection 集合
     * @param <T>        T
     * @return 空：true，非空：false
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 列表创建
     *
     * @param param 参数
     * @param <T>   T
     * @return 列表
     */
    @SafeVarargs
    public static <T> List<T> newArrayList(T... param) {
        return Arrays.asList(param);
    }

    /**
     * 列表创建
     *
     * @param size  期望大小
     * @param param 参数
     * @param <T>   T
     * @return 列表
     */
    @SafeVarargs
    public static <T> List<T> newArrayList(int size, T... param) {
        List<T> list = new ArrayList<>(Math.max(size, param.length));
        list.addAll(Arrays.asList(param));
        return list;
    }

    /**
     * Set创建
     *
     * @param param 参数
     * @param <T>   T
     * @return Set
     */
    @SafeVarargs
    public static <T> Set<T> newHashSet(T... param) {
        return new HashSet<>(Arrays.asList(param));
    }

    /**
     * 创建指定长度 HashMap
     *
     * @param <K> K
     * @param <V> V
     * @return HashMap
     */
    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>(capacity(16));
    }

    /**
     * 创建指定长度 HashMap
     *
     * @param expectedSize 期望长度
     * @param <K>          K
     * @param <V>          V
     * @return HashMap
     */
    public static <K, V> Map<K, V> newHashMap(int expectedSize) {
        return new HashMap<>(capacity(expectedSize));
    }

    /**
     * 根据参数创建 Map
     *
     * @param function 映射规则
     * @param values   值列表
     * @param <K>      K
     * @param <V>      V
     * @return Map
     */
    @SafeVarargs
    public static <K, V> Map<K, V> newHashMap(Function<V, K> function, V... values) {
        return newHashMap(function, Arrays.asList(values));
    }

    /**
     * 根据参数创建 Map
     *
     * @param function 映射规则
     * @param values   值列表
     * @param <K>      K
     * @param <V>      V
     * @return Map
     */
    public static <K, V> Map<K, V> newHashMap(Function<V, K> function, Collection<V> values) {
        if (isEmpty(values)) {
            return newHashMap();
        }
        return LambdaTools.listToMap(values, function);
    }

    /**
     * 分组
     *
     * @param collection 集合
     * @param mapper     mapper
     * @param <T>        T
     * @param <R>        R
     * @return 数据
     */
    public static <T, R> Map<R, List<T>> groupBy(Collection<T> collection, Function<? super T, ? extends R> mapper) {
        if (CollectTools.isEmpty(collection)) {
            return new HashMap<>();
        }
        return collection.stream().collect(Collectors.groupingBy(mapper));
    }

    /**
     * 计算HashSet与HashMap期望大小
     *
     * @param expectedSize 期望大小
     * @return 计算后大小
     */
    private static int capacity(int expectedSize) {
        final int minSize = 3;
        if (expectedSize < minSize) {
            if (expectedSize < 0) {
                throw new CommonException("期望大小为正数，不是：" + expectedSize);
            }
            return expectedSize + 1;
        }
        if (expectedSize < MAX_POWER_OF_TWO) {
            return (int) ((float) expectedSize / 0.75F + 1.0F);
        }
        return Integer.MAX_VALUE;
    }

    private CollectTools() {
        throw new UnsupportedOperationException();
    }
}
