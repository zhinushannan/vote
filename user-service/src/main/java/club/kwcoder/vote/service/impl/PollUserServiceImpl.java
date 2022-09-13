package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.CandidateDOExample;
import club.kwcoder.vote.dataobject.PollDO;
import club.kwcoder.vote.dataobject.PollDOExample;
import club.kwcoder.vote.dataobject.VoteDOExample;
import club.kwcoder.vote.dto.PollDTO;
import club.kwcoder.vote.mapper.custom.PollCustomMapper;
import club.kwcoder.vote.mapper.generate.CandidateMapper;
import club.kwcoder.vote.mapper.generate.PollMapper;
import club.kwcoder.vote.mapper.generate.VoteMapper;
import club.kwcoder.vote.service.PollUserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
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
    @Override
    @Transactional
    public ResultBean<String> doPoll(PollDTO poll, int userId, String ipAddr) {
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

        return ResultBean.success("投票成功！");
    }
}
