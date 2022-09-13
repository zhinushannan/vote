package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dto.PollSortDTO;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.service.VoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("vote")
public class VoteUserController {

    @Autowired
    private VoteUserService voteUserService;

    @GetMapping("get")
    public ResultBean<VoteDTO> getVote(@RequestParam("voteId") Integer voteId) {
        int userId = 6;
        return voteUserService.getVote(voteId, userId);
    }

    @GetMapping("list")
    public ResultBean<PageBean<VoteDO>> list(@RequestParam("page") Integer page,
                                             @RequestParam("size") Integer size) {
        int userId = 6;
        return voteUserService.list(page, size, userId);
    }

    @GetMapping("sort")
    public ResultBean<List<PollSortDTO>> sort(@RequestParam("voteId") Integer voteId) {
        return voteUserService.sort(voteId);
    }

}
