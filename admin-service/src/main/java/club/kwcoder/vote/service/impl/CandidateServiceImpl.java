package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.CandidateDO;
import club.kwcoder.vote.dataobject.CandidateUserDO;
import club.kwcoder.vote.dataobject.CandidateVersioinDO;
import club.kwcoder.vote.dto.CandidateDTO;
import club.kwcoder.vote.service.CandidateService;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Override
    @Transactional
    public ResultBean<String> save(CandidateDTO candidate, Integer userId) {

        CandidateDO candidateDO = CandidateDO.builder()
                .image(candidate.getUrl())
                .candidateName(candidate.getName())
                .introductionMd(candidate.getIntroductionMd())
                .introductionHtml(candidate.getIntroductionHtml())
                .createTimestamp(new Date())
                .versionCurrent(candidate.getVersionId())
                .build();

        // 保存 candidateDO

        CandidateUserDO candidateUserDO = CandidateUserDO.builder()
                .candidateId(candidateDO.getCandidateId())
                .userId(userId)
                .build();

        // 保存 candidateUserDO

        CandidateVersioinDO candidateVersioinDO = BeanUtil.copyProperties(candidateDO, CandidateVersioinDO.class);
        candidateVersioinDO.setVersionId(candidate.getVersionId());
        candidateVersioinDO.setVersionDescription(candidate.getVersionDescription());
        candidateVersioinDO.setModifyTimestamp(candidateDO.getCreateTimestamp());

        // 保存 candidateVersioinDO

        return null;
    }

}
