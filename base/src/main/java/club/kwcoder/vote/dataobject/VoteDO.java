package club.kwcoder.vote.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vote
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDO implements Serializable {
    private Integer voteId;

    private Integer status;

    private Date createTimestamp;

    private String title;

    private String description;

    private Date deadline;

    private static final long serialVersionUID = 1L;
}