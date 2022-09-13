package club.kwcoder.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PollDTO {

    private Integer voteId;
    private Map<Integer, Integer> scores;

}
