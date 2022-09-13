package club.kwcoder.vote.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * candidate
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateDO implements Serializable {
    private Integer candidateId;

    private Date createTimestamp;

    private Integer versionCurrent;

    private static final long serialVersionUID = 1L;
}