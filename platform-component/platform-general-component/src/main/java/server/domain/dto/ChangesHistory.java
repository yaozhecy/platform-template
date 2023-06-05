package server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 56807
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangesHistory {
    private String created;
    private ChangesUser user;
}
