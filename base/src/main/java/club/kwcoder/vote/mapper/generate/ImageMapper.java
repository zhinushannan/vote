package club.kwcoder.vote.mapper.generate;

import club.kwcoder.vote.dataobject.ImageDO;
import club.kwcoder.vote.dataobject.ImageDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageMapper {
    long countByExample(ImageDOExample example);

    int deleteByExample(ImageDOExample example);

    int deleteByPrimaryKey(String md5);

    int insert(ImageDO record);

    int insertSelective(ImageDO record);

    List<ImageDO> selectByExample(ImageDOExample example);

    ImageDO selectByPrimaryKey(String md5);

    int updateByExampleSelective(@Param("record") ImageDO record, @Param("example") ImageDOExample example);

    int updateByExample(@Param("record") ImageDO record, @Param("example") ImageDOExample example);

    int updateByPrimaryKeySelective(ImageDO record);

    int updateByPrimaryKey(ImageDO record);
}