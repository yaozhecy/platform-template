package com.cy.generate.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * HistoryVo
 *
 * @author 56807
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryVo {
    private String id;
    public String created;
    public String key;
    public Integer version;
    public String serverVersion;
    public UserInfoVo user;
    public List<HistoryItemVo> changes;
}
