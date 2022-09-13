package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.*;
import club.kwcoder.vote.dto.RegisterDTO;
import club.kwcoder.vote.mapper.generate.ProvinceMapper;
import club.kwcoder.vote.mapper.generate.RoleMapper;
import club.kwcoder.vote.mapper.generate.UserMapper;
import club.kwcoder.vote.mapper.generate.UserRoleMapper;
import club.kwcoder.vote.service.RegisterService;
import club.kwcoder.vote.util.StrCustomUtils;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public ResultBean<List<ProvinceDO>> register(RegisterDTO register) {
        if (!StrCustomUtils.isLengthAllowContainsAll(register.getPassword(), 6, 11) ||
                register.getPhone().length() != 11 ||
                !ReUtil.isMatch("^1[3456789]\\d{9}$", register.getPhone())) {
            return ResultBean.forbidden("参数有误！");
        }

        UserDOExample example = new UserDOExample();
        example.createCriteria()
                .andPhoneEqualTo(register.getPhone());
        List<UserDO> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            List<ProvinceDO> provinces = provinceMapper.selectByExample(new ProvinceDOExample());
            return ResultBean.success("注册成功，请按提示进一步完成信息！", provinces);
        }
        return registerByHasRecord(users.get(0));
    }


    /**
     * 没有用户记录的注册
     */
    private ResultBean<List<ProvinceDO>> registerByNoRecord(RegisterDTO register) {
        UserDO userDO = UserDO.builder()
                .phone(register.getPhone())
                .password(register.getPassword())
                .createTimestamp(new Date())
                .build();
        userMapper.insertSelective(userDO);
        List<ProvinceDO> provinces = provinceMapper.selectByExample(new ProvinceDOExample());
        return ResultBean.success("注册成功，请按提示进一步完成信息！", provinces);
    }

    /**
     * 有用户记录的注册
     */
    private ResultBean<List<ProvinceDO>> registerByHasRecord(UserDO user) {
        UserRoleDOExample userRoleDOExample = new UserRoleDOExample();
        userRoleDOExample.createCriteria()
                .andUserIdEqualTo(user.getUserId());
        List<UserRoleDO> userRoleDOS = userRoleMapper.selectByExample(userRoleDOExample);
        if (userRoleDOS.size() == 2) {
            return ResultBean.forbidden("您已经拥有普通用户和管理员身份！");
        }
        UserRoleDO userRoleDO = userRoleDOS.get(0);
        RoleDOExample roleDOExample = new RoleDOExample();
        roleDOExample.createCriteria()
                .andRoleIdEqualTo(userRoleDO.getRoleId());
        RoleDO roleDO = roleMapper.selectByExample(roleDOExample).get(0);
        return ResultBean.continueStatus("您已经拥有" + roleDO.getRole() + "角色，是否确认拥有另一角色？");
    }


    /**
     * 完善信息
     *
     * @param register 注册传输对象
     * @return 返回统一返回值
     */
    @Override
    @Transactional
    public ResultBean<String> info(RegisterDTO register) {
        if (register.getProvinceId() < 1 || register.getProvinceId() > 34 ||
                register.getRoleId() < 1 || register.getRoleId() > 2 ||
                StrCustomUtils.isAnyBlank(register.getPhone(), register.getPassword(), register.getName(), register.getSex())) {
            return ResultBean.forbidden("参数有误！");
        }

        if (!StrCustomUtils.isLengthAllowContainsAll(register.getPassword(), 6, 11) ||
                !StrCustomUtils.isLengthAllowContainsAll(register.getName(), 1, 10) ||
                register.getPhone().length() != 11) {
            return ResultBean.forbidden("参数有误！");
        }

        if (!StrUtil.equalsAny(register.getSex(), "male", "female") ||
                !ReUtil.isMatch("^1[3456789]\\d{9}$", register.getPhone())) {
            return ResultBean.forbidden("参数有误！");
        }

        // 插入用户
        UserDO userDO = UserDO.builder()
                .phone(register.getPhone())
                .name(register.getName())
                .password(register.getPassword())
                .provinceId(register.getProvinceId())
                .createTimestamp(new Date())
                .build();
        userMapper.insertSelective(userDO);

        System.out.println(userDO);
        // 插入权限
        UserRoleDO userRoleDO = UserRoleDO.builder()
                .roleId(register.getRoleId())
                .userId(userDO.getUserId())
                .build();
        userRoleMapper.insert(userRoleDO);
        return ResultBean.success("注册成功！");
    }

    /**
     * 添加权限
     *
     * @param register 注册传输对象
     * @return 返回统一返回值
     */
    @Override
    public ResultBean<String> addRole(RegisterDTO register) {
        UserDOExample userDOExample = new UserDOExample();
        userDOExample.createCriteria()
                .andPhoneEqualTo(register.getPhone());
        List<UserDO> users = userMapper.selectByExample(userDOExample);
        if (users.size() == 0) {
            return ResultBean.forbidden("参数错误");
        }
        UserDO user = users.get(0);
        if (!StrUtil.equals(user.getPassword(), register.getPassword())) {
            return ResultBean.forbidden("密码错误！");
        }
        UserRoleDOExample userRoleDOExample = new UserRoleDOExample();
        userRoleDOExample.createCriteria()
                .andUserIdEqualTo(user.getUserId());
        List<UserRoleDO> userRoleDOS = userRoleMapper.selectByExample(userRoleDOExample);
        UserRoleDO userRoleDO = userRoleDOS.get(0);
        userRoleDO.setRoleId(userRoleDO.getRoleId() == 1 ? 2 : 1);
        userRoleMapper.insert(userRoleDO);
        return ResultBean.success("添加权限成功！即将眺往登陆页面！");
    }
}
