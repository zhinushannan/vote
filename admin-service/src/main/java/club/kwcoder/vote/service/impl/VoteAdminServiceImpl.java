package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.*;
import club.kwcoder.vote.dto.PollSortDTO;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.mapper.custom.CandidateDTOMapper;
import club.kwcoder.vote.mapper.custom.PollSortDTOCustomMapper;
import club.kwcoder.vote.mapper.custom.VoteCandidateCustomMapper;
import club.kwcoder.vote.mapper.custom.VoteCustomMapper;
import club.kwcoder.vote.mapper.generate.*;
import club.kwcoder.vote.service.VoteAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private PollSortDTOCustomMapper pollSortDTOCustomMapper;

    @Autowired
    private CandidateDTOMapper candidateDTOMapper;

    @Autowired
    private VoteCandidateMapper voteCandidateMapper;

    @Autowired
    private CandidateUserMapper candidateUserMapper;

    @Autowired
    private CandidateMapper candidateMapper;

    @Autowired
    private CandidateVersioinMapper candidateVersioinMapper;

    @Autowired
    private PollMapper pollMapper;

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
    public ResultBean<PageBean<VoteDO>> list(Integer page, Integer size, int status, int userId) {
        PageHelper.startPage(page, size);
        List<VoteDO> votes = voteCustomMapper.selectByUserIdAndStatus(userId, status);
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

        voteDO.setDeadline(new Date());
        voteDO.setStatus(VoteDTO.OFF);
        voteMapper.updateByPrimaryKey(voteDO);

        return ResultBean.success("停止成功！");
    }

    @Override
    public ResultBean<List<PollSortDTO>> sort(Integer voteId) {
        VoteCandidateDOExample voteCandidateDOExample = new VoteCandidateDOExample();
        voteCandidateDOExample.createCriteria().andVoteIdEqualTo(voteId);
        List<Integer> candidateIds = voteCandidateMapper.selectByExample(voteCandidateDOExample).stream().map(VoteCandidateDO::getCandidateId).collect(Collectors.toList());

        Map<Integer, PollSortDTO> pollSortDTOMap = pollSortDTOCustomMapper.selectByVoteIdGroupByCandidateIdCountScoreMap(voteId);
        List<PollSortDTO> pollSortDTOs = candidateDTOMapper.selectByCandidateIdsCurrentVersion(candidateIds).stream().map(candidateDTO -> {
            PollSortDTO pollSortDTO = pollSortDTOMap.get(candidateDTO.getCandidateId());
            return PollSortDTO.builder()
                    .voteId(voteId)
                    .candidateId(candidateDTO.getCandidateId())
                    .score(pollSortDTO == null ? 0 : pollSortDTO.getScore())
                    .candidateName(candidateDTO.getName())
                    .build();
        }).collect(Collectors.toList());

        return ResultBean.success("查询成功！", pollSortDTOs);
    }

    @Override
    @Transactional
    public ResultBean<String> delete(Integer voteId) {
        VoteUserDOExample voteUserDOExample = new VoteUserDOExample();
        voteUserDOExample.createCriteria().andVoteIdEqualTo(voteId);
        voteUserMapper.deleteByExample(voteUserDOExample);

        PollDOExample pollDOExample = new PollDOExample();
        pollDOExample.createCriteria().andVoteIdEqualTo(voteId);
        pollMapper.deleteByExample(pollDOExample);

        VoteCandidateDOExample voteCandidateDOExample = new VoteCandidateDOExample();
        voteCandidateDOExample.createCriteria().andCandidateIdEqualTo(voteId);
        voteCandidateMapper.deleteByExample(voteCandidateDOExample);

        voteMapper.deleteByPrimaryKey(voteId);

        return ResultBean.success("删除成功！");
    }


}
