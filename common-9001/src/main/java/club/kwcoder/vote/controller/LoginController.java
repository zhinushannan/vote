package club.kwcoder.vote.controller;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.LoginDTO;
import club.kwcoder.vote.service.LoginService;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    public static final String BUSINESS = "公共-登录";
    public static final String login = "登录";
    public static final String logout = "登出";


    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public ResultBean<String> login(@RequestBody LoginDTO login,
                                    HttpServletResponse response) {
        ResultBean<String> result = loginService.login(login);

        String[] split = result.getData().split("\\|");

        String jwt = result.getMessage();
        response.addHeader("Access-Control-Expose-Headers", "authorization, rol, name");
        response.addHeader("authorization", jwt);
        response.addHeader("rol", split[1]);
        response.addHeader("name", split[0]);

        result.setData(null);
        result.setMessage("登录成功！");
        return result;
    }

    @GetMapping("logout")
    public ResultBean<String> logout(HttpServletResponse response) {
        response.setHeader("authorization", null);
        return ResultBean.success("登出成功！");
    }

}
