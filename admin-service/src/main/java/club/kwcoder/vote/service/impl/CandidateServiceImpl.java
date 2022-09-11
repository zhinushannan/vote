package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.CandidateDO;
import club.kwcoder.vote.dataobject.CandidateUserDO;
import club.kwcoder.vote.dataobject.CandidateVersioinDO;
import club.kwcoder.vote.dto.CandidateDTO;
import club.kwcoder.vote.mapper.generate.CandidateMapper;
import club.kwcoder.vote.mapper.generate.CandidateUserMapper;
import club.kwcoder.vote.mapper.generate.CandidateVersioinMapper;
import club.kwcoder.vote.service.CandidateService;
import club.kwcoder.vote.util.StrCustomUtils;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private CandidateUserMapper candidateUserMapper;
    @Autowired
    private CandidateVersioinMapper candidateVersioinMapper;

    @Override
    @Transactional
    public ResultBean<String> save(CandidateDTO candidate, Integer userId) {
        if (StrCustomUtils.isAnyBlank(candidate.getName(), candidate.getAbstractOfCandidate(),
                candidate.getImgUrl(), candidate.getIntroductionHtml(), candidate.getIntroductionMd())) {
            return ResultBean.forbidden("参数有误！");
        }

        if (!StrCustomUtils.isLengthAllowContainsAll(candidate.getName(), 1, 10) &&
                !StrCustomUtils.isLengthAllowContainsAll(candidate.getAbstractOfCandidate(), 0, 500) &&
                candidate.getVersionId() < 0) {
            return ResultBean.forbidden("参数有误！");
        }

        CandidateDO candidateDO = CandidateDO.builder()
                .image(candidate.getImgUrl())
                .candidateName(candidate.getName())
                .introductionMd(candidate.getIntroductionMd())
                .introductionHtml(candidate.getIntroductionHtml())
                .createTimestamp(new Date())
                .versionCurrent(candidate.getVersionId())
                .build();
        candidateMapper.insertSelective(candidateDO);

        CandidateUserDO candidateUserDO = CandidateUserDO.builder()
                .candidateId(candidateDO.getCandidateId())
                .userId(userId)
                .build();
        candidateUserMapper.insertSelective(candidateUserDO);

        CandidateVersioinDO candidateVersioinDO = BeanUtil.copyProperties(candidateDO, CandidateVersioinDO.class);
        candidateVersioinDO.setVersionId(candidate.getVersionId());
        candidateVersioinDO.setVersionDescription(candidate.getVersionDescription());
        candidateVersioinDO.setModifyTimestamp(candidateDO.getCreateTimestamp());
        candidateVersioinMapper.insertSelective(candidateVersioinDO);

        return ResultBean.success("添加成功！");
    }

}
