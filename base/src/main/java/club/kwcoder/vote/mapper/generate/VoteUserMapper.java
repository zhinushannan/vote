package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.VoteUserDO;
import club.kwcoder.vote.dataobject.VoteUserDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VoteUserMapper {
    long countByExample(VoteUserDOExample example);

    int deleteByExample(VoteUserDOExample example);

    int insert(VoteUserDO record);

    int insertSelective(VoteUserDO record);

    List<VoteUserDO> selectByExample(VoteUserDOExample example);

    int updateByExampleSelective(@Param("record") VoteUserDO record, @Param("example") VoteUserDOExample example);

    int updateByExample(@Param("record") VoteUserDO record, @Param("example") VoteUserDOExample example);
}