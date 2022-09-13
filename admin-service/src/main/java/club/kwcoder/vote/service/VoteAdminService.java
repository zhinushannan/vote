package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dto.PollSortDTO;
import club.kwcoder.vote.dto.VoteDTO;

import java.util.List;

public interface VoteAdminService {

    ResultBean<String> save(VoteDTO vote, int userId);

    ResultBean<PageBean<VoteDO>> list(Integer page, Integer size, int status, int userId);

    ResultBean<String> stop(Integer voteId);

    ResultBean<List<PollSortDTO>> sort(Integer voteId);

}
