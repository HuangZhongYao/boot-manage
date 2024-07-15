package org.github.zuuuyao.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.entity.AbstractBaseEntity;
import org.github.zuuuyao.entity.enums.ResourcesTypeEnum;

/**
 * 资源表(菜单表)
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-14 1:22
 */
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_resources")
public class ResourcesEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = 6833038591176768850L;

    /**
     * 编码
     */
    private String code;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 描述
     */
    private String description;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 是否显示
     */
    private Boolean isShow;

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * keepAlive 是否组件之间切换时缓存它们的状态
     */
    private Boolean keepAlive;

    /**
     * 资源类型
     */
    private ResourcesTypeEnum type;

    /**
     * 排序
     */
    private String sort;

    /**
     * 上级资源id
     */
    private Long parentId;
}
