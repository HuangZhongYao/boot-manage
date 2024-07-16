package org.github.zuuuyao.service.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.output.BaseOutputIdAndTimeDTO;
import org.github.zuuuyao.entity.enums.GenderEnum;
import org.github.zuuuyao.entity.system.RoleEntity;
import org.github.zuuuyao.service.auth.model.ResourcesVo;
import org.github.zuuuyao.service.role.dto.output.RoleVo;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-15 19:38
 */
@Schema(description = "认证用户信息")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthenticationUserDetailOutputDTO extends BaseOutputIdAndTimeDTO {

    @Serial
    private static final long serialVersionUID = 1668055235809325493L;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 账号
     */
    @Schema(description = "账号")
    private String account;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private GenderEnum gender;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 头像url
     */
    @Schema(description = "头像url")
    private String avatarUrl;

    /**
     * 角色列表
     */
    @Schema(description = "角色列表")
    private List<RoleVo> roles = new ArrayList<>();

    /**
     * 权限列表
     */
    @Schema(description = "权限列表")
    private List<ResourcesVo> permissions = new ArrayList<>();
}
