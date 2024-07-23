package org.github.zuuuyao.service.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-15 19:20
 */
@Schema(description = "登录参数模型", name = "登录请求参数")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoginInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 6250000014475264360L;

    @Schema(description = "验证码")
    private String captcha;

    @ValidateNotNullAndEmpty(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @ValidateNotNullAndEmpty(message = "登录账号不能为空")
    @Schema(description = "账号")
    private String account;
}
