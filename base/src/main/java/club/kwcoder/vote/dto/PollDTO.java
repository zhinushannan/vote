package club.kwcoder.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PollDTO {

    private Integer voteId;
    private Integer score;
    private String ip;
    private Date createTimestamp;

    private Integer userId;
    private String username;

    private String candidateName;

    private Map<Integer, Integer> scores;

}
