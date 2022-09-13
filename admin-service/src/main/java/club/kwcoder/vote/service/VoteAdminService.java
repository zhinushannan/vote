package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dto.VoteDTO;

public interface VoteAdminService {

    ResultBean<String> save(VoteDTO vote, int userId);

    ResultBean<PageBean<VoteDO>> list(Integer page, Integer size, int userId);

    ResultBean<String> stop(Integer voteId);
}
