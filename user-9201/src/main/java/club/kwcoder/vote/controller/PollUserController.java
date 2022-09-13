package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.PollDTO;
import club.kwcoder.vote.service.PollUserService;
import club.kwcoder.vote.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

}
