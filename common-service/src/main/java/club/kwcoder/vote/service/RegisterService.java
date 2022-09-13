package club.kwcoder.vote.service;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.ProvinceDO;
import club.kwcoder.vote.dto.RegisterDTO;

import java.util.List;

public interface RegisterService {

    /**
     * 注册的方法
     *
     * @param register 注册传输对象
     * @return 返回统一返回值
     */
    ResultBean<List<ProvinceDO>> register(RegisterDTO register);

    /**
     * 完善信息
     *
     * @param register 注册传输对象
     * @return 返回统一返回值
     */
    ResultBean<String> info(RegisterDTO register);

    /**
     * 添加权限
     *
     * @param register 注册传输对象
     * @return 返回统一返回值
     */
    ResultBean<String> addRole(RegisterDTO register);
}
