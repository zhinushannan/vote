package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.PollDTO;

import java.util.List;

public interface PollAdminService {

    ResultBean<List<PollDTO>> info(int voteId);

}
