package org.github.zuuuyao.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.output.BaseOutputIdAndTimeAndOperationDTO;
import org.github.zuuuyao.common.base.entity.AbstractBaseEntity;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-16 19:46
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_role")
public class UserRoleEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -2510671353986711029L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户id
     */
    private Long userId;
}
