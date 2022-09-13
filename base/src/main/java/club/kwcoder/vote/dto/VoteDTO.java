package club.kwcoder.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDTO {

    public static final Integer ON = 0;
    public static final Integer OFF = 1;

    private Integer voteId;
    private String name;
    private String description;
    private Date deadline;
    private List<Integer> candidateIds;
    private List<CandidateDTO> candidates;
    private Map<Integer, Integer> scores;


}
