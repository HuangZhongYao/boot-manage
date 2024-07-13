package org.github.zuuuyao.service.role.dto.input;

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
 * @Time: 2024-07-13 19:23
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddRoleInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 4216643512169478985L;

    @ValidateNotNullAndEmpty(message = "图标不能为空")
    @Schema(description = "图标")
    private String icon;

    @Schema(description = "角色名称")
    @ValidateNotNullAndEmpty(message = "角色名称不能为空")
    private String name;

    @Schema(description = "描述")
    private String remark;
}
