package club.kwcoder.vote.dataobject;

import java.io.Serializable;
import lombok.Data;

/**
 * role
 * @author 
 */
@Data
public class RoleDO implements Serializable {
    private Integer roleId;

    private String role;

    private static final long serialVersionUID = 1L;
}