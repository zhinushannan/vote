package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.VoteCandidateDO;
import club.kwcoder.vote.dataobject.VoteCandidateDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VoteCandidateMapper {
    long countByExample(VoteCandidateDOExample example);

    int deleteByExample(VoteCandidateDOExample example);

    int insert(VoteCandidateDO record);

    int insertSelective(VoteCandidateDO record);

    List<VoteCandidateDO> selectByExample(VoteCandidateDOExample example);

    int updateByExampleSelective(@Param("record") VoteCandidateDO record, @Param("example") VoteCandidateDOExample example);

    int updateByExample(@Param("record") VoteCandidateDO record, @Param("example") VoteCandidateDOExample example);
}