package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.VoteCandidateDO;
import club.kwcoder.vote.dataobject.VoteCandidateDOExample;
import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dataobject.VoteDOExample;
import club.kwcoder.vote.dto.CandidateDTO;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.mapper.custom.CandidateDTOMapper;
import club.kwcoder.vote.mapper.custom.VoteCandidateCustomMapper;
import club.kwcoder.vote.mapper.generate.VoteCandidateMapper;
import club.kwcoder.vote.mapper.generate.VoteMapper;
import club.kwcoder.vote.service.VoteUserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class VoteUserServiceImpl implements VoteUserService {

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteCandidateMapper voteCandidateMapper;

    @Autowired
    private CandidateDTOMapper candidateDTOMapper;

    @Override
    public ResultBean<VoteDTO> getVote(int voteId) {
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

        return ResultBean.success("获取成功！", VoteDTO.builder()
                .voteId(voteId)
                .name(voteDO.getTitle())
                .description(voteDO.getDescription())
                .deadline(voteDO.getDeadline())
                .candidateIds(candidateIds)
                .candidates(candidateDTOS)
                .build());
    }
}
