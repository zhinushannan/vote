package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dto.CandidateDTO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CandidateDTOMapper {

    List<CandidateDTO> selectByCandidateIdsCurrentVersion(@Param("candidateIds") List<Integer> candidateIds);

    @MapKey("candidateId")
    Map<Integer, CandidateDTO> selectByCandidateIdsCurrentVersionMap(@Param("candidateIds") List<Integer> candidateIds);

}
