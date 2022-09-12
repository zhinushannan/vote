package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("save")
    public ResultBean<String> save(@RequestBody VoteDTO vote) {
        int userId = 6;
        return voteService.save(vote, userId);
    }

}
