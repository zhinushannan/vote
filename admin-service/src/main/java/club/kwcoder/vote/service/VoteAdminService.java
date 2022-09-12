package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.VoteDTO;

public interface VoteAdminService {

    ResultBean<String> save(VoteDTO vote, int userId);

    ResultBean<PageBean<VoteDTO>> list(Integer page, Integer size, int userId);
}
