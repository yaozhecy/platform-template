package com.cy.platform.gateway.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("TB_QX_ZHXX")
public class AccountInfoPo {
    @TableId("CID")
    private String id;

    @TableField("CZHMC")
    private String accountName;

    @TableField("CZHMM")
    private String accoutPwd;
}
