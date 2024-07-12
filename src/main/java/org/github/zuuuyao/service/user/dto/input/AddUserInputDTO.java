package org.github.zuuuyao.service.user.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.entity.enums.GenderEnum;

import java.io.Serial;

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
     * 用户名
     */
    @Schema(description = "用户名",example = "宇宙无敌的高手")
    private String username;

    /**
     * 账号
     */
    @Schema(description = "账号",example = "huangzy")
    private String account;

    /**
     * 性别
     */
    @Schema(description = "性别",example = "MALE")
    private GenderEnum gender;

    /**
     * 手机号
     */
    @Schema(description = "手机号",example = "17785306043")
    private String phone;

    /**
     * 头像url
     */
    @Schema(description = "头像url",example = "https://avatars.githubusercontent.com/u/46741470?v=4&size=256")
    private String avatarUrl;
}
