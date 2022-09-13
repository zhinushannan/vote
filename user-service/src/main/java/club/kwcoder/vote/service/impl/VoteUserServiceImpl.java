package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.*;
import club.kwcoder.vote.dto.CandidateDTO;
import club.kwcoder.vote.dto.PollSortDTO;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.mapper.custom.CandidateDTOMapper;
import club.kwcoder.vote.mapper.custom.PollCustomMapper;
import club.kwcoder.vote.mapper.custom.PollSortDTOCustomMapper;
import club.kwcoder.vote.mapper.custom.VoteCustomMapper;
import club.kwcoder.vote.mapper.generate.PollMapper;
import club.kwcoder.vote.mapper.generate.VoteCandidateMapper;
import club.kwcoder.vote.mapper.generate.VoteMapper;
import club.kwcoder.vote.service.VoteUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoteUserServiceImpl implements VoteUserService {

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteCandidateMapper voteCandidateMapper;

    @Autowired
    private VoteCustomMapper voteCustomMapper;

    @Autowired
    private PollSortDTOCustomMapper pollSortDTOCustomMapper;

    @Autowired
    private CandidateDTOMapper candidateDTOMapper;

    @Autowired
    private PollMapper pollMapper;

    @Autowired
    private PollCustomMapper pollCustomMapper;

    @Override
    public ResultBean<VoteDTO> getVote(int voteId, int userId) {
        VoteDOExample voteDOExample = new VoteDOExample();
        voteDOExample.createCriteria()
                .andVoteIdEqualTo(voteId)
                .andStatusEqualTo(VoteDTO.ON);
        List<VoteDO> votes = voteMapper.selectByExample(voteDOExample);
        if (votes.size() == 0) {
            return ResultBean.forbidden("投票不存在或已结束！");
        }

        VoteDO voteDO = votes.get(0);
        if (voteDO.getDeadline() != null && voteDO.getDeadline().compareTo(new Date()) >= 0) {
            voteDO.setStatus(VoteDTO.OFF);
            voteMapper.updateByPrimaryKey(voteDO);
            return ResultBean.forbidden("投票不存在或已结束！");
        }

        VoteCandidateDOExample voteCandidateDOExample = new VoteCandidateDOExample();
        voteCandidateDOExample.createCriteria().andVoteIdEqualTo(voteId);
        List<Integer> candidateIds = voteCandidateMapper.selectByExample(voteCandidateDOExample).stream().map(VoteCandidateDO::getCandidateId).collect(Collectors.toList());
        List<CandidateDTO> candidateDTOS = candidateDTOMapper.selectByCandidateIdsCurrentVersion(candidateIds);

        PollDOExample pollDOExample = new PollDOExample();
        pollDOExample.createCriteria()
                .andVoteIdEqualTo(voteId)
                .andUserIdEqualTo(userId);

        Map<Integer, Integer> scores = pollMapper.selectByExample(pollDOExample).stream().collect(Collectors.toMap(PollDO::getCandidateId, PollDO::getScore));

        return ResultBean.success("获取成功！", VoteDTO.builder()
                .voteId(voteId)
                .name(voteDO.getTitle())
                .description(voteDO.getDescription())
                .deadline(voteDO.getDeadline())
                .candidateIds(candidateIds)
                .candidates(candidateDTOS)
                .scores(scores)
                .build());
    }

    @Override
    public ResultBean<PageBean<VoteDO>> list(Integer page, Integer size, int userId) {
        PageHelper.startPage(page, size);
        List<Integer> voteIds = pollCustomMapper.selectVoteIdsByUserId(userId);
        long total = PageInfo.of(voteIds).getTotal();

        VoteDOExample voteDOExample = new VoteDOExample();
        voteDOExample.createCriteria().andVoteIdIn(voteIds);
        List<VoteDO> voteDOS = voteMapper.selectByExample(voteDOExample);

        return ResultBean.success("查询成功！",
                PageBean.<VoteDO>builder()
                        .page(page)
                        .size(size)
                        .data(voteDOS)
                        .total(total)
                        .build()
        );
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

}
