package club.kwcoder.vote.dataobject;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user_role
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleDO implements Serializable {
    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}