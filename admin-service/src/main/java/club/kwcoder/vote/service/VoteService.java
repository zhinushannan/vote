package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.VoteDTO;

public interface VoteService {

    ResultBean<String> save(VoteDTO vote, int userId);

}
