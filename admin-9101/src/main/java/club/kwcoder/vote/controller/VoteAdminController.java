package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.service.VoteAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vote")
public class VoteAdminController {

    @Autowired
    private VoteAdminService voteService;

    @PostMapping("save")
    public ResultBean<String> save(@RequestBody VoteDTO vote) {
        int userId = 6;
        return voteService.save(vote, userId);
    }

    @GetMapping("list")
    public ResultBean<PageBean<VoteDO>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        int userId = 6;
        return voteService.list(page, size, userId);
    }

    @GetMapping("stop")
    public ResultBean<String> stop(@RequestParam("voteId") Integer voteId) {
        return voteService.stop(voteId);
    }

}
