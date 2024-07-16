package org.github.zuuuyao.service.user.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.entity.enums.GenderEnum;

import java.io.Serial;

/**
 * @Desc
 * @Time 2024-07-16 16:17
 * @Author HuangZhongYao
 */
@Getter
@Setter
@ToString
public class UserQueryPageInputDTO extends BaseQueryPageInputDTO {
    @Serial
    private static final long serialVersionUID = 3267321355395088871L;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "账号")
    private String account;

    @Schema(description = "性别")
    private GenderEnum gender;

    @Schema(description = "启用状态: true|false")
    private Boolean enable;
}
