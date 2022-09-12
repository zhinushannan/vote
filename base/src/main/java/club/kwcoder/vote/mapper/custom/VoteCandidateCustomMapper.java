package club.kwcoder.vote.mapper.custom;

import club.kwcoder.vote.dataobject.VoteCandidateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteCandidateCustomMapper {

    void insertBatch(List<VoteCandidateDO> list);

}
