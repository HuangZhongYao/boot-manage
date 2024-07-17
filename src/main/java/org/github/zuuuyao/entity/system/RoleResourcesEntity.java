package org.github.zuuuyao.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.github.zuuuyao.common.base.entity.AbstractBaseEntity;

import java.io.Serial;

/**
 * 系统角色权限表
 *
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-17 09:27
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_resources")
public class RoleResourcesEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -8866939458137795129L;

    /**
     * 资源|权限 Id
     */
    private Long resourcesId;

    /**
     * 角色id
     */
    private Long roleId;
}
