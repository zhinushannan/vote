package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.VoteCandidateDO;
import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dataobject.VoteUserDO;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.mapper.custom.VoteCandidateCustomMapper;
import club.kwcoder.vote.mapper.custom.VoteCustomMapper;
import club.kwcoder.vote.mapper.generate.VoteMapper;
import club.kwcoder.vote.mapper.generate.VoteUserMapper;
import club.kwcoder.vote.service.VoteAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VoteAdminServiceImpl implements VoteAdminService {

    @Autowired
    private VoteMapper voteMapper;
    @Autowired
    private VoteCustomMapper voteCustomMapper;
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
    public ResultBean<PageBean<VoteDO>> list(Integer page, Integer size, int userId) {
        PageHelper.startPage(page, size);
        List<VoteDO> votes = voteCustomMapper.selectByUserId(userId);
        long total = PageInfo.of(votes).getTotal();
        return ResultBean.success("获取成功！",
                PageBean.<VoteDO>builder()
                        .page(page)
                        .size(size)
                        .total(total)
                        .data(votes)
                        .build());
    }

    @Override
    public ResultBean<String> stop(Integer voteId) {
        VoteDO voteDO = voteMapper.selectByPrimaryKey(voteId);
        if (null == voteDO) {
            return ResultBean.notFound("投票不存在");
        }

        voteDO.setStatus(VoteDTO.OFF);
        voteMapper.updateByPrimaryKey(voteDO);

        return ResultBean.success("停止成功！");
    }
}
