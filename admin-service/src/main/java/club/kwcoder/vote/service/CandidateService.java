package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.CandidateVersioinDO;
import club.kwcoder.vote.dto.CandidateDTO;

import java.util.List;
import java.util.Map;

public interface CandidateService {

    ResultBean<String> save(CandidateDTO candidate, Integer userId);

    ResultBean<String> update(CandidateDTO candidate, Integer userId);

    ResultBean<PageBean<CandidateDTO>> list(Integer page, Integer size, int userId);

    ResultBean<List<CandidateVersioinDO>> history(Integer candidateId);

    ResultBean<String> delete(Integer candidateId);

    ResultBean<String> recovery(Integer candidateId, Integer versionId, int userId);
}
