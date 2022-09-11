package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.CandidateDTO;
import club.kwcoder.vote.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
