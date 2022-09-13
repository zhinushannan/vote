package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dto.PollDTO;
import club.kwcoder.vote.dto.PollSortDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PollSortDTOCustomMapper {

    List<PollSortDTO> selectByVoteIdGroupByCandidateIdCountScore(@Param("voteId") Integer voteId);

}
