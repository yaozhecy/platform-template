package server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 动作
 *
 * @author 56807
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Action {
    private String userid;
    private String type;
}
