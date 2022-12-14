package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.RoleDO;
import club.kwcoder.vote.dataobject.RoleDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    long countByExample(RoleDOExample example);

    int deleteByExample(RoleDOExample example);

    int deleteByPrimaryKey(RoleDO key);

    int insert(RoleDO record);

    int insertSelective(RoleDO record);

    List<RoleDO> selectByExample(RoleDOExample example);

    int updateByExampleSelective(@Param("record") RoleDO record, @Param("example") RoleDOExample example);

    int updateByExample(@Param("record") RoleDO record, @Param("example") RoleDOExample example);
}