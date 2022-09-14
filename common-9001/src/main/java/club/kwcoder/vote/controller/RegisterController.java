package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.ProvinceDO;
import club.kwcoder.vote.dto.RegisterDTO;
import club.kwcoder.vote.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("register")
public class RegisterController {

    public static final String BUSINESS = "公共-注册";
    public static final String register = "注册";
    public static final String info = "完善信息";
    public static final String addRole = "增加权限";
    @Autowired
    private RegisterService registerService;

    @PostMapping("do")
    public ResultBean<List<ProvinceDO>> register(@RequestBody RegisterDTO register) {
        return registerService.register(register);
    }

    @PostMapping("info")
    public ResultBean<String> info(@RequestBody RegisterDTO register) {
        return registerService.info(register);
    }

    @PostMapping("addRole")
    public ResultBean<String> addRole(@RequestBody RegisterDTO register) {
        return registerService.addRole(register);
    }

}
