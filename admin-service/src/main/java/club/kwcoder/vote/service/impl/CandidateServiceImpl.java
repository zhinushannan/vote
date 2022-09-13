package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.*;
import club.kwcoder.vote.dto.CandidateDTO;
import club.kwcoder.vote.mapper.custom.CandidateDTOMapper;
import club.kwcoder.vote.mapper.generate.CandidateMapper;
import club.kwcoder.vote.mapper.generate.CandidateUserMapper;
import club.kwcoder.vote.mapper.generate.CandidateVersioinMapper;
import club.kwcoder.vote.service.CandidateService;
import club.kwcoder.vote.util.StrCustomUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private CandidateUserMapper candidateUserMapper;
    @Autowired
    private CandidateVersioinMapper candidateVersioinMapper;

    @Autowired
    private CandidateDTOMapper candidateDTOMapper;

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
                .createTimestamp(new Date())
                .versionCurrent(0)
                .build();
        candidateMapper.insertSelective(candidateDO);

        CandidateUserDO candidateUserDO = CandidateUserDO.builder()
                .candidateId(candidateDO.getCandidateId())
                .userId(userId)
                .build();
        candidateUserMapper.insertSelective(candidateUserDO);

        CandidateVersioinDO candidateVersioinDO = CandidateVersioinDO.builder()
                .candidateId(candidateDO.getCandidateId())
                .image(candidate.getImgUrl())
                .candidateName(candidate.getName())
                .candidateAbstract(candidate.getAbstractOfCandidate())
                .introductionMd(candidate.getIntroductionMd())
                .introductionHtml(candidate.getIntroductionHtml())
                .versionId(0)
                .modifyTimestamp(candidateDO.getCreateTimestamp())
                .build();
        candidateVersioinMapper.insertSelective(candidateVersioinDO);

        return ResultBean.success("添加成功！");
    }

    @Override
    @Transactional
    public ResultBean<String> update(CandidateDTO candidate, Integer userId) {
        if (StrCustomUtils.isAnyBlank(candidate.getName(), candidate.getAbstractOfCandidate(),
                candidate.getImgUrl(), candidate.getIntroductionHtml(), candidate.getIntroductionMd())) {
            return ResultBean.forbidden("参数有误！");
        }

        if (!StrCustomUtils.isLengthAllowContainsAll(candidate.getName(), 1, 10) &&
                !StrCustomUtils.isLengthAllowContainsAll(candidate.getAbstractOfCandidate(), 0, 500) &&
                candidate.getVersionId() < 0) {
            return ResultBean.forbidden("参数有误！");
        }

        // 更新 CandidateDO，version + 1
        CandidateDOExample candidateDOExample = new CandidateDOExample();
        candidateDOExample.createCriteria()
                .andCandidateIdEqualTo(candidate.getCandidateId());
        List<CandidateDO> candidateDOS = candidateMapper.selectByExample(candidateDOExample);
        if (candidateDOS.size() == 0) {
            return ResultBean.forbidden("候选人不存在！");
        }

        CandidateDO candidateDO = candidateDOS.get(0);
        candidateDO.setVersionCurrent(candidateDO.getVersionCurrent() + 1);
        candidateMapper.updateByPrimaryKey(candidateDO);

        // 添加 CandidateVersioinDO
        CandidateVersioinDO candidateVersioinDO = CandidateVersioinDO.builder()
                .candidateId(candidateDO.getCandidateId())
                .image(candidate.getImgUrl())
                .candidateName(candidate.getName())
                .candidateAbstract(candidate.getAbstractOfCandidate())
                .introductionMd(candidate.getIntroductionMd())
                .introductionHtml(candidate.getIntroductionHtml())
                .versionId(candidateDO.getVersionCurrent())
                .modifyTimestamp(new Date())
                .build();
        candidateVersioinMapper.insertSelective(candidateVersioinDO);

        return ResultBean.success("更新成功！");
    }

    @Override
    public ResultBean<PageBean<CandidateDTO>> list(Integer page, Integer size, Map<String, Object> conditions) {
        // 分页查询candidateId
        if (page > 0) {
            PageHelper.startPage(page, size);
        }
        CandidateUserDOExample candidateUserDOExample = new CandidateUserDOExample();
        candidateUserDOExample.createCriteria()
                .andUserIdEqualTo((Integer) conditions.get("userId"));
        List<CandidateUserDO> candidateUserDOS = candidateUserMapper.selectByExample(candidateUserDOExample);
        List<Integer> candidateIds = candidateUserDOS.stream().map(CandidateUserDO::getCandidateId).collect(Collectors.toList());
        long total = PageInfo.of(candidateUserDOS).getTotal();

        // 查询candidateDto
        CandidateDOExample candidateDOExample = new CandidateDOExample();
        candidateDOExample.createCriteria()
                .andCandidateIdIn(candidateIds);
        List<Integer> ids = candidateMapper.selectByExample(candidateDOExample).stream().map(CandidateDO::getCandidateId).collect(Collectors.toList());
        List<CandidateDTO> candidateDTOS = candidateDTOMapper.selectByCandidateIdsCurrentVersion(ids);

        return ResultBean.success("查询成功！", PageBean.<CandidateDTO>builder()
                .page(page)
                .size(size)
                .total(total)
                .data(candidateDTOS)
                .build());
    }

    @Override
    public ResultBean<List<CandidateVersioinDO>> history(Integer candidateId) {
        CandidateVersioinDOExample candidateVersioinDOExample = new CandidateVersioinDOExample();
        candidateVersioinDOExample.createCriteria()
                .andCandidateIdEqualTo(candidateId);
        candidateVersioinDOExample.setOrderByClause("version_id");

        List<CandidateVersioinDO> candidateVersioinDOS = candidateVersioinMapper.selectByExample(candidateVersioinDOExample);

        return ResultBean.success("查询成功！", candidateVersioinDOS);
    }

    @Override
    @Transactional
    public ResultBean<String> delete(Integer candidateId) {
        CandidateVersioinDOExample candidateVersioinDOExample = new CandidateVersioinDOExample();
        candidateVersioinDOExample.createCriteria().andCandidateIdEqualTo(candidateId);
        candidateVersioinMapper.deleteByExample(candidateVersioinDOExample);

        CandidateUserDOExample candidateUserDOExample = new CandidateUserDOExample();
        candidateUserDOExample.createCriteria().andCandidateIdEqualTo(candidateId);
        candidateUserMapper.deleteByExample(candidateUserDOExample);

        CandidateDOExample candidateDOExample = new CandidateDOExample();
        candidateDOExample.createCriteria().andCandidateIdEqualTo(candidateId);
        candidateMapper.deleteByExample(candidateDOExample);

        return ResultBean.success("删除成功！");
    }

}
