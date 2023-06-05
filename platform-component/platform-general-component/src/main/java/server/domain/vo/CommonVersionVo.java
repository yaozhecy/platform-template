package server.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 56807
 */
@Data
public abstract class CommonVersionVo {
    private List<Integer> versions;
}
