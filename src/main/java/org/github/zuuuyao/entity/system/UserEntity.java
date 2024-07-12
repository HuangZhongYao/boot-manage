package org.github.zuuuyao.entity.system;

import java.io.Serial;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.github.zuuuyao.common.base.entity.AbstractBaseEntity;
import org.github.zuuuyao.entity.enums.GenderEnum;

/**
 * @Desc 系统用户表
 * @Time 2024-07-11 16:31
 * @Author HuangZhongYao
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class UserEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -4075127738715995785L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 性别
     */
    private GenderEnum gender;
}
