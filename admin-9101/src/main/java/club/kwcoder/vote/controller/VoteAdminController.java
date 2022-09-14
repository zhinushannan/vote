package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.VoteDO;
import club.kwcoder.vote.dto.PollSortDTO;
import club.kwcoder.vote.dto.VoteDTO;
import club.kwcoder.vote.service.VoteAdminService;
import club.kwcoder.vote.util.AuthorizationUtils;
import club.kwcoder.vote.util.DCSLockUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("vote")
public class VoteAdminController {

    public static final String BUSINESS = "管理员-投票";
    public static final String save = "发布";
    public static final String list = "分页查询";
    public static final String stop = "停止";
    public static final String sort = "获取排名";
    @Autowired
    private VoteAdminService voteService;
    @Autowired
    private DCSLockUtils dcsLockUtils;

    @PostMapping("save")
    public ResultBean<String> save(@RequestBody VoteDTO vote,
                                   HttpServletRequest request) {
        int userId = AuthorizationUtils.getUserId(request);
        return voteService.save(vote, userId);
    }

    @GetMapping("list")
    public ResultBean<PageBean<VoteDO>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "status") Integer status,
                                             HttpServletRequest request) {
        int userId = AuthorizationUtils.getUserId(request);
        return voteService.list(page, size, status, userId);
    }

    @GetMapping("stop")
    public ResultBean<String> stop(@RequestParam("voteId") Integer voteId) {
        boolean lock = dcsLockUtils.lock("vote:" + voteId, 5000);
        if (!lock) {
            return ResultBean.buys();
        }
        ResultBean<String> result = voteService.stop(voteId);
        dcsLockUtils.unlock("vote:" + voteId);
        return result;
    }

    @GetMapping("sort")
    public ResultBean<List<PollSortDTO>> sort(@RequestParam("voteId") Integer voteId) {
        return voteService.sort(voteId);
    }

    @GetMapping("delete")
    public ResultBean<String> delete(@RequestParam("voteId") Integer voteId) {
        return voteService.delete(voteId);
    }


}
