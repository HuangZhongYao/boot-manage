package org.github.zuuuyao.service.role.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-16 21:02
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RolePageQueryInputDTO extends BaseQueryPageInputDTO {

    @Serial
    private static final long serialVersionUID = 4631897722110504328L;

    @Schema(description = "角色名")
    private String name;

    @Schema(description = "启用状态")
    private Boolean enable;
}
