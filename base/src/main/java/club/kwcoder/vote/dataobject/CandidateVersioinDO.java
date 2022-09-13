package club.kwcoder.vote.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * candidate_versioin
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateVersioinDO implements Serializable {
    private Integer candidateId;

    private String image;

    private String candidateName;

    private String candidateAbstract;

    private String introductionMd;

    private String introductionHtml;

    private Date modifyTimestamp;

    private Integer versionId;

    private String versionDescription;

    private static final long serialVersionUID = 1L;
}