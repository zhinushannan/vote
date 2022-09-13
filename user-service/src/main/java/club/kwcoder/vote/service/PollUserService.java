package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.PollDTO;

import java.util.List;

public interface PollUserService {

    ResultBean<String> doPoll(PollDTO poll, int userId, String ipAddr);

    ResultBean<List<PollDTO>> info(Integer voteId, int userId);
}
