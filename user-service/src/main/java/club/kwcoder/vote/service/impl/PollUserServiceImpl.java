package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.*;
import club.kwcoder.vote.dto.PollDTO;
import club.kwcoder.vote.mapper.custom.CandidateDTOMapper;
import club.kwcoder.vote.mapper.custom.PollCustomMapper;
import club.kwcoder.vote.mapper.generate.CandidateMapper;
import club.kwcoder.vote.mapper.generate.PollMapper;
import club.kwcoder.vote.mapper.generate.VoteCandidateMapper;
import club.kwcoder.vote.mapper.generate.VoteMapper;
import club.kwcoder.vote.service.PollUserService;
import club.kwcoder.vote.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PollUserServiceImpl implements PollUserService {

    @Autowired
    private PollMapper pollMapper;

    @Autowired
    private PollCustomMapper pollCustomMapper;

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private CandidateMapper candidateMapper;

    @Autowired
    private CandidateDTOMapper candidateDTOMapper;

    @Autowired
    private VoteCandidateMapper voteCandidateMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional
    public ResultBean<String> doPoll(PollDTO poll, int userId, String ipAddr) {
        Object o = redisUtils.get("vote:" + poll.getVoteId() + ":" + userId);
        if (null != o) {
            return ResultBean.forbidden("您的投票过于频繁，请稍后重试！");
        }

        Integer voteId = poll.getVoteId();
        VoteDOExample voteDOExample = new VoteDOExample();
        voteDOExample.createCriteria().andVoteIdEqualTo(voteId);
        if (voteMapper.countByExample(voteDOExample) != 1) {
            return ResultBean.forbidden("投票不存在！");
        }

        Map<Integer, Integer> scores = poll
                .getScores()
                .entrySet()
                .stream()
                .filter(integerIntegerEntry -> integerIntegerEntry.getValue() != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (scores.size() == 0) {
            return ResultBean.success("投票成功！");
        }

        List<Integer> candidateIds = new ArrayList<>(scores.keySet());
        CandidateDOExample candidateDOExample = new CandidateDOExample();
        candidateDOExample.createCriteria().andCandidateIdIn(candidateIds);
        if (candidateMapper.countByExample(candidateDOExample) != candidateIds.size()) {
            return ResultBean.forbidden("候选人不存在！");
        }

        PollDOExample pollDOExample = new PollDOExample();
        pollDOExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andVoteIdEqualTo(voteId);
        pollMapper.deleteByExample(pollDOExample);

        Date date = new Date();
        List<PollDO> polls = scores.entrySet().stream().map(integerIntegerEntry -> PollDO.builder()
                .userId(userId)
                .voteId(voteId)
                .candidateId(integerIntegerEntry.getKey())
                .score(integerIntegerEntry.getValue())
                .ip(ipAddr)
                .createTimestamp(date)
                .build()).collect(Collectors.toList());

        pollCustomMapper.insertBatch(polls);

        redisUtils.set("vote:" + poll.getVoteId() + ":" + userId, "flag", 60);
        return ResultBean.success("投票成功！");
    }

    @Override
    public ResultBean<List<PollDTO>> info(Integer voteId, int userId) {
        // 查询投票记录
        PollDOExample pollDOExample = new PollDOExample();
        pollDOExample.createCriteria()
                .andVoteIdEqualTo(voteId)
                .andUserIdEqualTo(userId);
        Map<Integer, PollDO> pollDOMap = pollCustomMapper.selectByExample(pollDOExample);

        // 查询所有候选人
        VoteCandidateDOExample voteCandidateDOExample = new VoteCandidateDOExample();
        voteCandidateDOExample.createCriteria()
                .andVoteIdEqualTo(voteId);
        List<Integer> candidateIds = voteCandidateMapper.selectByExample(voteCandidateDOExample).stream().map(VoteCandidateDO::getCandidateId).collect(Collectors.toList());

        // 组装对象
        List<PollDTO> pollDTOS = candidateDTOMapper.selectByCandidateIdsCurrentVersion(candidateIds).stream().map(candidateDTO -> {
            PollDO pollDO = pollDOMap.get(candidateDTO.getCandidateId());
            return PollDTO.builder()
                    .voteId(voteId)
                    .score(pollDO == null ? 0 : pollDO.getScore())
                    .ip(pollDO == null ? null : pollDO.getIp())
                    .createTimestamp(pollDO == null ? null : pollDO.getCreateTimestamp())
                    .userId(userId)
                    .candidateName(candidateDTO.getName())
                    .build();
        }).collect(Collectors.toList());

        return ResultBean.success("查询成功！", pollDTOS);
    }
}
