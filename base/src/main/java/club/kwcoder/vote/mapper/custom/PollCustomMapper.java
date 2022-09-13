package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dataobject.PollDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PollCustomMapper {

    int insertBatch(@Param("polls") List<PollDO> polls);

}
