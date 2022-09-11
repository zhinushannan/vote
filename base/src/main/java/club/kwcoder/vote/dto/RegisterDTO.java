package club.kwcoder.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册使用的数据传输类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    /**
     * 用户的手机号
     */
    private String phone;
    /**
     * 用户的密码
     */
    private String password;

    /**
     * 用户的姓名
     */
    private String name;
    /**
     * 用户的省份
     */
    private Integer provinceId;
    /**
     * 性别
     */
    private String sex;
    /**
     * 用户的角色
     */
    private Integer roleId;
}
