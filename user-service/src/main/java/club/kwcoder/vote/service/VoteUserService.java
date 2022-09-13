package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dto.PollSortDTO;
import club.kwcoder.vote.dto.VoteDTO;

import java.util.List;

public interface VoteUserService {

    ResultBean<VoteDTO> getVote(int voteId, int userId);

    ResultBean<PageBean<VoteDO>> list(Integer page, Integer size, int userId);

    ResultBean<List<PollSortDTO>> sort(Integer voteId);
}
