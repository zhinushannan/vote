package club.kwcoder.vote.dataobject;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * candidate_user
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateUserDO implements Serializable {
    private Integer candidateId;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}