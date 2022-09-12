package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.VoteCandidateDO;
import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dataobject.VoteUserDO;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.mapper.custom.VoteCandidateCustomMapper;
import club.kwcoder.vote.mapper.generate.VoteCandidateMapper;
import club.kwcoder.vote.mapper.generate.VoteMapper;
import club.kwcoder.vote.mapper.generate.VoteUserMapper;
import club.kwcoder.vote.service.VoteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteMapper voteMapper;
    @Autowired
    private VoteCandidateCustomMapper voteCandidateCustomMapper;
    @Autowired
    private VoteUserMapper voteUserMapper;

    @Override
    public ResultBean<String> save(VoteDTO vote, int userId) {
        VoteDO voteDO = VoteDO.builder()
                .status(0)
                .createTimestamp(new Date())
                .title(vote.getName())
                .description(vote.getDescription())
                .deadline(vote.getDeadline())
                .build();
        voteMapper.insertSelective(voteDO);

        List<Integer> ids = vote.getCandidateIds();
        List<VoteCandidateDO> voteCandidateDOS = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            voteCandidateDOS.add(new VoteCandidateDO(voteDO.getVoteId(), ids.get(i), i));
        }
        voteCandidateCustomMapper.insertBatch(voteCandidateDOS);

        voteUserMapper.insertSelective(VoteUserDO.builder().voteId(voteDO.getVoteId()).userId(userId).build());

        return ResultBean.success("插入成功！");
    }

    @Override
    public ResultBean<PageBean<VoteDTO>> list(Integer page, Integer size, int userId) {
        PageHelper.startPage(page, size);

        return null;
    }
}
