package club.kwcoder.vote.dataobject;

import lombok.Data;

import java.io.Serializable;

/**
 * role
 *
 * @author
 */
@Data
public class RoleDO implements Serializable {
    private Integer roleId;

    private String role;

    private static final long serialVersionUID = 1L;
}