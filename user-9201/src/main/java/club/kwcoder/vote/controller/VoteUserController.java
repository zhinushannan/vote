package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.service.VoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vote")
public class VoteUserController {

    @Autowired
    private VoteUserService voteUserService;

    @GetMapping("get")
    public ResultBean<VoteDTO> getVote(@RequestParam("voteId") Integer voteId) {
        return voteUserService.getVote(voteId);
    }

}
