package org.github.zuuuyao.service.user.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.github.zuuuyao.common.base.dto.output.BaseOutputIdAndTimeAndOperationDTO;
import org.github.zuuuyao.entity.enums.GenderEnum;
import org.github.zuuuyao.service.role.dto.output.RoleVo;
import org.github.zuuuyao.service.user.model.UserRoleModel;

/**
 * @Desc
 * @Time 2024-07-16 16:29
 * @Author HuangZhongYao
 */
@Getter
@Setter
public class UserVo extends BaseOutputIdAndTimeAndOperationDTO {

    @Serial
    private static final long serialVersionUID = -7091624991626890336L;

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

    /**
     * 用户角色列表
     */
    @Schema(description = "用户角色列表")
    private List<UserRoleModel> roles;
}
