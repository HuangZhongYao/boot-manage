package org.github.zuuuyao.service.resources.dto.input;

import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.entity.enums.ResourcesTypeEnum;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-14 2:37
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddResourcesInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 4380271138282804417L;

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
    private Boolean show;

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
    private String order;

    /**
     * 上级资源id
     */
    private String parentId;
}
