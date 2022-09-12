package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dto.CandidateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CandidateDTOMapper {

    List<CandidateDTO> selectByCandidateIdsCurrentVersion(@Param("candidateIds") List<Integer> candidateIds);

}
