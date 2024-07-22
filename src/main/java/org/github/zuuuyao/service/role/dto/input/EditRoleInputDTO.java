package org.github.zuuuyao.service.role.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.github.zuuuyao.common.base.dto.input.BaseLongIdInputDTO;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-16 21:21
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EditRoleInputDTO extends BaseLongIdInputDTO {

    @Serial
    private static final long serialVersionUID = 4929873507751718934L;

    @Schema(description = "角色名")
    private String name;

    @Schema(description = "启用状态 true|false")
    private Boolean enable;

    @Schema(description = "图标", example = "i-fe:user")
    private String icon;

    @Schema(description = "描述", example = "拥有系统全部权限")
    private String remark;

    @Schema(description = "权限id集合")
    private List<Long> permissionIds = new ArrayList<>();
}
