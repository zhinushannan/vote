package club.kwcoder.vote.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vote_candidate
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteCandidateDO implements Serializable {
    private Integer voteId;

    private Integer candidateId;

    private Integer sort;

    private static final long serialVersionUID = 1L;
}