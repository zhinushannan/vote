package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dataobject.VoteDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VoteMapper {
    long countByExample(VoteDOExample example);

    int deleteByExample(VoteDOExample example);

    int deleteByPrimaryKey(Integer voteId);

    int insert(VoteDO record);

    int insertSelective(VoteDO record);

    List<VoteDO> selectByExample(VoteDOExample example);

    VoteDO selectByPrimaryKey(Integer voteId);

    int updateByExampleSelective(@Param("record") VoteDO record, @Param("example") VoteDOExample example);

    int updateByExample(@Param("record") VoteDO record, @Param("example") VoteDOExample example);

    int updateByPrimaryKeySelective(VoteDO record);

    int updateByPrimaryKey(VoteDO record);
}