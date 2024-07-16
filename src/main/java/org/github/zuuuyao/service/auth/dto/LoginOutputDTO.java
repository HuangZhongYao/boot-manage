package org.github.zuuuyao.service.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.entity.enums.GenderEnum;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-15 19:21
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoginOutputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 6906705936429510608L;

    /**
     * 访问Token
     */
    @Schema(description = "访问Token令牌")
    private String accessToken;

    /**
     * 用户名
     */
    @Schema(description = "访问Token令牌")
    private String username;

    /**
     * 账号
     */
    @Schema(description = "访问Token令牌")
    private String account;

    /**
     * 性别
     */
    @Schema(description = "访问Token令牌")
    private GenderEnum gender;

    /**
     * 手机号
     */
    @Schema(description = "访问Token令牌")
    private String phone;

    /**
     * 头像url
     */
    @Schema(description = "访问Token令牌")
    private String avatarUrl;
}
