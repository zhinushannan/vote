package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.UserRoleDO;
import club.kwcoder.vote.dataobject.UserRoleDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {
    long countByExample(UserRoleDOExample example);

    int deleteByExample(UserRoleDOExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UserRoleDO record);

    int insertSelective(UserRoleDO record);

    List<UserRoleDO> selectByExample(UserRoleDOExample example);

    UserRoleDO selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UserRoleDO record, @Param("example") UserRoleDOExample example);

    int updateByExample(@Param("record") UserRoleDO record, @Param("example") UserRoleDOExample example);

    int updateByPrimaryKeySelective(UserRoleDO record);

    int updateByPrimaryKey(UserRoleDO record);
}