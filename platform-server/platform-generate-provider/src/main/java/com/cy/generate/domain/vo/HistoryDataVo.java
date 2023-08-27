package com.cy.generate.domain.vo;

import lombok.Data;

import java.util.Map;

/**
 * HistoryVo
 *
 * @author 56807
 */
@Data
public class HistoryDataVo {
    /**
     * key
     */
    public String key;
    /**
     * 文档地址
     */
    public String url;
    /**
     * 修改记录
     */
    public String changesUrl;
    /**
     * 版本
     */
    public Integer version;
    /**
     * 上版本
     */
    public Map<String, String> previous;
}
