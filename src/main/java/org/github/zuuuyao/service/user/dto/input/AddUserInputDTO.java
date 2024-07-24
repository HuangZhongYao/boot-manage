package org.github.zuuuyao.service.user.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;
import org.github.zuuuyao.common.validate.ValidatePhone;
import org.github.zuuuyao.common.validate.ValidateURL;
import org.github.zuuuyao.common.validate.group.Group;
import org.github.zuuuyao.entity.enums.GenderEnum;

import java.io.Serial;
import java.util.ArrayList;

/**
 * @Desc
 * @Time 2024-07-12 11:02
 * @Author HuangZhongYao
 */
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "添加用户DTO")
public class AddUserInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = -5329610125578371097L;

    /**
     * 头像地址
     */
    @Schema(description = "头像", example = "https://sfsd.com/fins/tx.png",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String avatarUrl = "/defaultAvatar.png";

    /**
     * 用户名
     */
    @Schema(description = "用户名", example = "宇宙无敌的高手",requiredMode = Schema.RequiredMode.REQUIRED)
    @ValidateNotNullAndEmpty(message = "用户名不能为空",groups = {Group.Insert.class,Group.Edit.class})
    private String username;

    /**
     * 账号
     */
    @Schema(description = "账号", example = "huangzy",requiredMode = Schema.RequiredMode.REQUIRED)
    @ValidateNotNullAndEmpty(message = "账号不能为空",groups = Group.Insert.class)
    private String account;

    /**
     * 登录密码
     */
    @Schema(description = "密码", example = "123456",requiredMode = Schema.RequiredMode.REQUIRED)
    @ValidateNotNullAndEmpty(message = "密码不能为空",groups = {Group.Insert.class,Group.Edit.class})
    private String password;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 角色id
     */
    @Schema(description = "角色id",example = "[1]")
    @ValidateNotNullAndEmpty(message = "角色不能为空",groups = {Group.Insert.class,Group.Edit.class})
    private ArrayList<Long> roleIds;
}
