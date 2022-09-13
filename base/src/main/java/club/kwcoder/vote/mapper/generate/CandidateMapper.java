package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.CandidateDO;
import club.kwcoder.vote.dataobject.CandidateDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CandidateMapper {
    long countByExample(CandidateDOExample example);

    int deleteByExample(CandidateDOExample example);

    int deleteByPrimaryKey(Integer candidateId);

    int insert(CandidateDO record);

    int insertSelective(CandidateDO record);

    List<CandidateDO> selectByExample(CandidateDOExample example);

    CandidateDO selectByPrimaryKey(Integer candidateId);

    int updateByExampleSelective(@Param("record") CandidateDO record, @Param("example") CandidateDOExample example);

    int updateByExample(@Param("record") CandidateDO record, @Param("example") CandidateDOExample example);

    int updateByPrimaryKeySelective(CandidateDO record);

    int updateByPrimaryKey(CandidateDO record);
}