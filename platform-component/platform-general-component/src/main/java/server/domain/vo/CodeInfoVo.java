package server.domain.vo;

import lombok.Data;

/**
 * CodeInfo
 *
 * @author 56807
 */
@Data
public class CodeInfoVo {
    private String vo;
    private String dto;
    private String po;
    private String transfor;
    private String mapper;
    private String mapperXml;
    private String service;
    private String serviceImpl;
    private String controller;
    private String sql;
}
