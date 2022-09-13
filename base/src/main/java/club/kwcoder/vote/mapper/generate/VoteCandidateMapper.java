package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.VoteCandidateDO;
import club.kwcoder.vote.dataobject.VoteCandidateDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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