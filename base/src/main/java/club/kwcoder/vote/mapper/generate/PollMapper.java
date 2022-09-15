package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.PollDO;
import club.kwcoder.vote.dataobject.PollDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PollMapper {
    long countByExample(PollDOExample example);

    int deleteByExample(PollDOExample example);

    int insert(PollDO record);

    int insertSelective(PollDO record);

    List<PollDO> selectByExample(PollDOExample example);

    int updateByExampleSelective(@Param("record") PollDO record, @Param("example") PollDOExample example);

    int updateByExample(@Param("record") PollDO record, @Param("example") PollDOExample example);
}