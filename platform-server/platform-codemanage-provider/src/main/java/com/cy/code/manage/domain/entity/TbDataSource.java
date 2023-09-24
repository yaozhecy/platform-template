package com.cy.code.manage.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * 数据源档案信息
 *
 * @author chenyang
 */
@Entity
@Table(schema = "tb_data_source")
public class TbDataSource {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 数据源类型
     */
    private Integer sourceType;

    /**
     * 创建人
     */
    private String creatorId;

    /**
     * 创建人姓名
     */

    private String creatorName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updaterId;

    /**
     * 修改人姓名
     */
    private String updaterName;
    
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
