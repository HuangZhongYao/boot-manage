package org.github.zuuuyao.service.user.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.github.zuuuyao.common.base.dto.input.BaseLongIdInputDTO;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;

import java.io.Serial;

/**
 * 重置密码接口接受参数对象
 *
 * @Desc
 * @Time 2024-07-16 14:58
 * @Author HuangZhongYao
 */
@Getter
@Setter
public class ResetPasswordInputDTO extends BaseLongIdInputDTO {

    @Serial
    private static final long serialVersionUID = -233739729144636666L;

    @Schema(description = "新密码")
    @ValidateNotNullAndEmpty(message = "密码不能为空")
    private String password;
}
