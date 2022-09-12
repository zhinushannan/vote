package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.PageBean;
import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.CandidateVersioinDO;
import club.kwcoder.vote.dto.CandidateDTO;
import club.kwcoder.vote.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("save")
    public ResultBean<String> save(@RequestBody CandidateDTO candidate) {
        Integer userId = 6;
        return candidateService.save(candidate, userId);
    }

    @PostMapping("update")
    public ResultBean<String> update(@RequestBody CandidateDTO candidate) {
        Integer userId = 6;
        return candidateService.update(candidate, userId);
    }

    @GetMapping("list")
    public ResultBean<PageBean<CandidateDTO>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "8") Integer size) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("userId", 6);
        return candidateService.list(page, size, conditions);
    }

    @GetMapping("history")
    public ResultBean<List<CandidateVersioinDO>> history(@RequestParam("candidateId") Integer candidateId) {
        return candidateService.history(candidateId);
    }

    @GetMapping("delete")
    public ResultBean<String> delete(@RequestParam("candidateId") Integer candidateId) {
        Integer userId = 6;
        return candidateService.delete(candidateId);
    }


}
