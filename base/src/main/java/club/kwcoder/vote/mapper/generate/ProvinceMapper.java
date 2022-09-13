package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.ProvinceDO;
import club.kwcoder.vote.dataobject.ProvinceDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProvinceMapper {
    long countByExample(ProvinceDOExample example);

    int deleteByExample(ProvinceDOExample example);

    int deleteByPrimaryKey(Integer provinceId);

    int insert(ProvinceDO record);

    int insertSelective(ProvinceDO record);

    List<ProvinceDO> selectByExample(ProvinceDOExample example);

    ProvinceDO selectByPrimaryKey(Integer provinceId);

    int updateByExampleSelective(@Param("record") ProvinceDO record, @Param("example") ProvinceDOExample example);

    int updateByExample(@Param("record") ProvinceDO record, @Param("example") ProvinceDOExample example);

    int updateByPrimaryKeySelective(ProvinceDO record);

    int updateByPrimaryKey(ProvinceDO record);
}