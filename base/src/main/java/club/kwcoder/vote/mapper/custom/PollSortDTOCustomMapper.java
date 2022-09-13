package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dto.PollSortDTO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PollSortDTOCustomMapper {

    List<PollSortDTO> selectByVoteIdGroupByCandidateIdCountScore(@Param("voteId") Integer voteId);

    @MapKey("candidateId")
    Map<Integer, PollSortDTO> selectByVoteIdGroupByCandidateIdCountScoreMap(@Param("voteId") Integer voteId);

}
