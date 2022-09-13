package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dataobject.VoteDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteCustomMapper {

    List<VoteDO> selectByUserId(Integer userId);

}
