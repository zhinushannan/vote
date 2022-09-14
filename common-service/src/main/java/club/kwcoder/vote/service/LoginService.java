package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dto.LoginDTO;

public interface LoginService {
    ResultBean<String> login(LoginDTO login);
}
