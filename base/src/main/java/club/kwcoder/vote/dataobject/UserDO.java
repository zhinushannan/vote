package club.kwcoder.vote.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDO implements Serializable {
    private Integer userId;

    private String phone;

    private String name;

    private String password;

    private Integer provinceId;

    private Date createTimestamp;

    private static final long serialVersionUID = 1L;
}