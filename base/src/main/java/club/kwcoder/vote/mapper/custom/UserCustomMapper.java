package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dataobject.UserDO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserCustomMapper {

    @MapKey("userId")
    Map<Integer, UserDO> selectByUserIds(@Param("userIds") List<Integer> userIds);

}
