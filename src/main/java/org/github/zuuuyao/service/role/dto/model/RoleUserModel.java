package org.github.zuuuyao.service.role.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.output.BaseOutputDTO;
import org.github.zuuuyao.entity.enums.GenderEnum;

/**
 * 角色下的用户
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-21 16:17
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleUserModel extends BaseOutputDTO {

    @Serial
    private static final long serialVersionUID = -3191921659477946369L;

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
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 启用状态
     */
    @Schema(description = "启用状态")
    private Boolean enable;
}
