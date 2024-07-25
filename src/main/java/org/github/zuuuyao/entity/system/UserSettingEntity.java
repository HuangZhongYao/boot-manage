package org.github.zuuuyao.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.github.zuuuyao.common.base.entity.AbstractBaseEntity;

import java.io.Serial;

/**
 * 用户个人设置
 * @Desc
 * @Time 2024-07-25 15:42
 * @Author HuangZhongYao
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_setting")
public class UserSettingEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -3827499303136111091L;

    /**
     * 布局方式
     */
    private String layoutMode;

    /**
     * 主题色
     */
    private String theme;
}
