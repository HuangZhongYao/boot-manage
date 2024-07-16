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
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.common.base.dto.input.BaseLongIdInputDTO;

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

    /**
     * 启用状态
     */
    @Schema(description = "启用状态 true|false")
    private Boolean enable;
}
