package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.PollDTO;
import club.kwcoder.vote.service.PollUserService;
import club.kwcoder.vote.util.AuthorizationUtils;
import club.kwcoder.vote.util.DCSLockUtils;
import club.kwcoder.vote.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("poll")
public class PollUserController {

    public static final String BUSINESS = "用户-投票信息";
    public static final String doPoll = "投票";
    public static final String info = "投票详情";
    @Autowired
    private PollUserService pollUserService;
    @Autowired
    private DCSLockUtils dcsLockUtils;

    @PostMapping("do")
    public ResultBean<String> doPoll(@RequestBody PollDTO poll,
                                     HttpServletRequest request) {
        boolean lock = dcsLockUtils.lock("vote:" + poll.getVoteId(), 5000);
        if (!lock) {
            return ResultBean.buys();
        }
        int userId = AuthorizationUtils.getUserId(request);
        String ipAddr = IpUtil.getIpAddr(request);
        ResultBean<String> result = pollUserService.doPoll(poll, userId, ipAddr);
        dcsLockUtils.unlock("vote:" + poll.getVoteId());
        return result;
    }

    @GetMapping("info")
    public ResultBean<List<PollDTO>> info(@RequestParam("voteId") Integer voteId,
                                          HttpServletRequest request) {
        int userId = AuthorizationUtils.getUserId(request);
        return pollUserService.info(voteId, userId);
    }

}
