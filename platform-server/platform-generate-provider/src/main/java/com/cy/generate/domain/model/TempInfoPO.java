package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("temp_info")
public class TempInfoPO {
    @TableId("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;
}
