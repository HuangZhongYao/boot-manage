package org.github.zuuuyao.service.resources.dto.input;

import java.io.Serial;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;
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
    @ValidateNotNullAndEmpty(message = "编码不能为空")
    @Schema(description = "资源编码",requiredMode = Schema.RequiredMode.REQUIRED,example = "RoleMgt")
    private String code;

    /**
     * 资源名称
     */
    @ValidateNotNullAndEmpty(message = "编码不能为空")
    @Schema(description = "资源编码",requiredMode = Schema.RequiredMode.REQUIRED,example = "角色管理")
    private String name;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址",example = "/pms/role")
    private String path;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径",example = "/src/views/pms/role/index.vue")
    private String component;

    /**
     * 描述
     */
    @Schema(description = "描述",example = "xx功能页面")
    private String description;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标",example = "i-fe:user")
    private String icon;

    /**
     * 是否显示
     */
    @Schema(description = "菜单图标",example = "true")
    private Boolean isShow;

    /**
     * 是否启用
     */
    @ValidateNotNullAndEmpty(message = "是否启用状态不能为空")
    @Schema(description = "是否启用",requiredMode = Schema.RequiredMode.REQUIRED,example = "true")
    private Boolean enable;

    /**
     * keepAlive 是否组件之间切换时缓存它们的状态
     */
    @Schema(description = "keepAlive是否组件之间切换时缓存它们的状态",example = "true")
    private Boolean keepAlive;

    /**
     * 资源类型
     */
    @ValidateNotNullAndEmpty(message = "资源类型不能为空")
    @Schema(description = "资源类型",requiredMode = Schema.RequiredMode.REQUIRED,example = "MENU")
    private ResourcesTypeEnum type;

    /**
     * 排序
     */
    @Schema(description = "排序值越低越靠前",example = "1")
    private String sort;

    /**
     * 上级资源id
     */
    @ValidateNotNullAndEmpty(message = "所属菜单Id")
    @Schema(description = "所属菜单Id",requiredMode = Schema.RequiredMode.REQUIRED,example = "1")
    private String parentId;
}
