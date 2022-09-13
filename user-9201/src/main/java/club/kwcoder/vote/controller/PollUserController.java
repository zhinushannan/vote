package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.PollDTO;
import club.kwcoder.vote.service.PollUserService;
import club.kwcoder.vote.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("poll")
public class PollUserController {

    @Autowired
    private PollUserService pollUserService;

    @PostMapping("do")
    public ResultBean<String> doPoll(@RequestBody PollDTO poll,
                                     HttpServletRequest request) {
        int userId = 6;
        String ipAddr = IpUtil.getIpAddr(request);
        return pollUserService.doPoll(poll, userId, ipAddr);
    }

    @GetMapping("info")
    public ResultBean<List<PollDTO>> info(@RequestParam("voteId") Integer voteId) {
        int userId = 6;
        return pollUserService.info(voteId, userId);
    }

}
