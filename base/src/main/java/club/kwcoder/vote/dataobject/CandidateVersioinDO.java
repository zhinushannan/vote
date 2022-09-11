package club.kwcoder.vote.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * candidate_versioin
 * @author 
 */
@Data
public class CandidateVersioinDO implements Serializable {
    private Integer candidateId;

    private String image;

    private String candidateName;

    private String introductionMd;

    private String introductionHtml;

    private Date modifyTimestamp;

    private Integer versionId;

    private String versionDescription;

    private static final long serialVersionUID = 1L;
}