package com.cy.platform.model.redis.constant;

/**
 * Redis常量
 *
 * @author 56807
 */
public final class RedisConstant {
    /**
     * 单例模式
     */
    public static final String SINGLE_MODE = "single";
    /**
     * 集群模式
     */
    public static final String CLUSTERS_MODE = "clusters";
    /**
     * 哨兵模式
     */
    public static final String SENTINEL_MODE = "sentinel";

    private RedisConstant() {
        throw new UnsupportedOperationException();
    }
}
