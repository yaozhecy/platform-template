package server.domain.dto;

import lombok.Data;

/**
 * 版本参数
 *
 * @author 56807
 */
@Data
public class CommonVersionDto {
    /**
     * 业务ID
     */
    private String businessId;
    /**
     * 业务编码
     */
    private String businessCode;
    /**
     * 版本号
     */
    private Integer version;
}
