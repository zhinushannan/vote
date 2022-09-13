package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dataobject.PollDO;
import club.kwcoder.vote.dataobject.PollDOExample;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PollCustomMapper {

    int insertBatch(@Param("polls") List<PollDO> polls);

    List<Integer> selectVoteIdsByUserId(@Param("userId") Integer userId);

    @MapKey("candidateId")
    Map<Integer, PollDO> selectByExample(PollDOExample example);

}
