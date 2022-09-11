package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.CandidateVersioinDO;
import club.kwcoder.vote.dataobject.CandidateVersioinDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CandidateVersioinMapper {
    long countByExample(CandidateVersioinDOExample example);

    int deleteByExample(CandidateVersioinDOExample example);

    int insert(CandidateVersioinDO record);

    int insertSelective(CandidateVersioinDO record);

    List<CandidateVersioinDO> selectByExample(CandidateVersioinDOExample example);

    int updateByExampleSelective(@Param("record") CandidateVersioinDO record, @Param("example") CandidateVersioinDOExample example);

    int updateByExample(@Param("record") CandidateVersioinDO record, @Param("example") CandidateVersioinDOExample example);
}