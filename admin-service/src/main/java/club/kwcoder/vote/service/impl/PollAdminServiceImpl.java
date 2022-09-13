package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.PollDO;
import club.kwcoder.vote.dataobject.PollDOExample;
import club.kwcoder.vote.dataobject.UserDO;
import club.kwcoder.vote.dto.CandidateDTO;
import club.kwcoder.vote.dto.PollDTO;
import club.kwcoder.vote.mapper.custom.CandidateDTOMapper;
import club.kwcoder.vote.mapper.custom.UserCustomMapper;
import club.kwcoder.vote.mapper.generate.PollMapper;
import club.kwcoder.vote.service.PollAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PollAdminServiceImpl implements PollAdminService {

    @Autowired
    private PollMapper pollMapper;

    @Autowired
    private CandidateDTOMapper candidateDTOMapper;

    @Autowired
    private UserCustomMapper userCustomMapper;

    @Override
    public ResultBean<List<PollDTO>> info(int voteId) {
        PollDOExample pollDOExample = new PollDOExample();
        pollDOExample.createCriteria().andVoteIdEqualTo(voteId);

        List<PollDO> pollDOS = pollMapper.selectByExample(pollDOExample);

        if (pollDOS.size() == 0) {
            return ResultBean.success("查询成功！");
        }

        List<Integer> candidateIds = pollDOS.stream().map(PollDO::getCandidateId).distinct().collect(Collectors.toList());
        Map<Integer, CandidateDTO> candidateDTOMap = candidateDTOMapper.selectByCandidateIdsCurrentVersionMap(candidateIds);

        List<Integer> userIds = pollDOS.stream().map(PollDO::getUserId).distinct().collect(Collectors.toList());
        Map<Integer, UserDO> userDOMap = userCustomMapper.selectByUserIds(userIds);

        List<PollDTO> pollDTOS = pollDOS.stream().map(pollDO -> PollDTO.builder()
                .voteId(voteId)
                .score(pollDO.getScore())
                .ip(pollDO.getIp())
                .createTimestamp(pollDO.getCreateTimestamp())
                .userId(pollDO.getUserId())
                .username(userDOMap.get(pollDO.getUserId()).getName())
                .candidateName(candidateDTOMap.get(pollDO.getCandidateId()).getName())
                .build()).collect(Collectors.toList());

        return ResultBean.success("查询成功！", pollDTOS);
    }
}
