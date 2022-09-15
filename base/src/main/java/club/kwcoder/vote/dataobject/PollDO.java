package club.kwcoder.vote.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * poll
 *
 * @author
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollDO implements Serializable {
    private Integer userId;

    private Integer voteId;

    private Integer candidateId;

    private Integer score;

    private String ip;

    private String ipAddr;

    private Date createTimestamp;

    private static final long serialVersionUID = 1L;
}