package club.kwcoder.vote.dataobject;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vote_user
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteUserDO implements Serializable {
    private Integer voteId;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}