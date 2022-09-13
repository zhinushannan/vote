package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.PollDTO;
import club.kwcoder.vote.service.PollAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("poll")
public class PollAdminController {

    @Autowired
    private PollAdminService pollAdminService;

    @GetMapping("info")
    public ResultBean<List<PollDTO>> info(@RequestParam("voteId") Integer voteId) {
        return pollAdminService.info(voteId);
    }

}
