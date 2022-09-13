package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dataobject.VoteDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VoteCustomMapper {

    List<VoteDO> selectByUserIdAndStatus(@Param("userId") Integer userId,
                                         @Param("status") Integer status);


}
