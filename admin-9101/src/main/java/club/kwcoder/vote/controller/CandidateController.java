package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.CandidateVersioinDO;
import club.kwcoder.vote.dto.CandidateDTO;
import club.kwcoder.vote.service.CandidateService;
import club.kwcoder.vote.util.AuthorizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("candidate")
public class CandidateController {

    public static final String BUSINESS = "管理员-候选人";
    public static final String save = "保存";
    public static final String update = "更新";
    public static final String list = "分页查询";
    public static final String history = "历史版本";
    public static final String delete = "删除";
    public static final String recovery = "恢复版本";

    @Autowired
    private CandidateService candidateService;

    @PostMapping("save")
    public ResultBean<String> save(@RequestBody CandidateDTO candidate,
                                   HttpServletRequest request) {
        int userId = AuthorizationUtils.getUserId(request);
        return candidateService.save(candidate, userId);
    }

    @PostMapping("update")
    public ResultBean<String> update(@RequestBody CandidateDTO candidate,
                                     HttpServletRequest request) {
        int userId = AuthorizationUtils.getUserId(request);
        return candidateService.update(candidate, userId);
    }

    @GetMapping("list")
    public ResultBean<PageBean<CandidateDTO>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "8") Integer size,
                                                   HttpServletRequest request) {
        int userId = AuthorizationUtils.getUserId(request);
        return candidateService.list(page, size, userId);
    }

    @GetMapping("history")
    public ResultBean<List<CandidateVersioinDO>> history(@RequestParam("candidateId") Integer candidateId) {
        return candidateService.history(candidateId);
    }

    @GetMapping("delete")
    public ResultBean<String> delete(@RequestParam("candidateId") Integer candidateId) {
        return candidateService.delete(candidateId);
    }


    @GetMapping("recovery")
    public ResultBean<String> recovery(@RequestParam("candidateId") Integer candidateId,
                                       @RequestParam("versionId") Integer versionId,
                                       HttpServletRequest request) {
        int userId = AuthorizationUtils.getUserId(request);
        return candidateService.recovery(candidateId, versionId, userId);
    }

}
