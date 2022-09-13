package club.kwcoder.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PollSortDTO {

    private Integer voteId;
    private Integer candidateId;
    private Integer score;
    private String candidateName;

}
