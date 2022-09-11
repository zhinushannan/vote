package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.CandidateUserDO;
import club.kwcoder.vote.dataobject.CandidateUserDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CandidateUserMapper {
    long countByExample(CandidateUserDOExample example);

    int deleteByExample(CandidateUserDOExample example);

    int insert(CandidateUserDO record);

    int insertSelective(CandidateUserDO record);

    List<CandidateUserDO> selectByExample(CandidateUserDOExample example);

    int updateByExampleSelective(@Param("record") CandidateUserDO record, @Param("example") CandidateUserDOExample example);

    int updateByExample(@Param("record") CandidateUserDO record, @Param("example") CandidateUserDOExample example);
}