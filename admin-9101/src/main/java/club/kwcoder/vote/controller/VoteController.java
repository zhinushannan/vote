package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("list")
    public ResultBean<PageBean<VoteDTO>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
        int userId = 6;
        return voteService.list(page, size, userId);
    }

}
