package club.kwcoder.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateDTO {

    private String name;
    private String abstractOfCandidate;
    private String url;
    private String introductionMd;
    private String introductionHtml;
    private Integer versionId = 0;
    private String versionDescription;

}
