package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.CandidateDTO;

public interface CandidateService {

    ResultBean<String> save(CandidateDTO candidate, Integer userId);

}
