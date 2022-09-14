package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.*;
import club.kwcoder.vote.dto.LoginDTO;
import club.kwcoder.vote.mapper.generate.RoleMapper;
import club.kwcoder.vote.mapper.generate.UserMapper;
import club.kwcoder.vote.mapper.generate.UserRoleMapper;
import club.kwcoder.vote.service.LoginService;
import club.kwcoder.vote.util.RedisUtils;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ResultBean<String> login(LoginDTO login) {
        UserDOExample example = new UserDOExample();
        example.createCriteria().andPhoneEqualTo(login.getPhone());
        List<UserDO> users = userMapper.selectByExample(example);

        if (users.size() == 0) {
            return ResultBean.forbidden("用户尚未注册");
        }

        UserDO user = users.get(0);
        if (!StrUtil.equals(login.getPassword(), user.getPassword())) {
            return ResultBean.forbidden("用户名或密码错误！");
        }

        UserRoleDOExample userRoleDOExample = new UserRoleDOExample();
        userRoleDOExample.createCriteria().andUserIdEqualTo(user.getUserId());
        List<Integer> roleIds = userRoleMapper.selectByExample(userRoleDOExample).stream().map(UserRoleDO::getRoleId).collect(Collectors.toList());

        RoleDOExample roleDOExample = new RoleDOExample();
        roleDOExample.createCriteria().andRoleIdIn(roleIds);
        String roles = roleMapper.selectByExample(roleDOExample).stream().map(RoleDO::getRole).collect(Collectors.toList()).toString();

        String jwt = JWT.create()
                .setPayload("phe", login.getPhone())
                .setPayload("id", user.getUserId())
                .setPayload("rol", roles)
                .setKey(jwtKey.getBytes())
                .setExpiresAt(DateUtil.tomorrow())
                .sign();

        redisUtils.set("jwt:" + login.getPhone(), jwt);

        return ResultBean.success(jwt, user.getName() + "|" + roles);
    }
}
