package org.github.zuuuyao.service.user.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.ArrayList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.common.base.dto.input.BaseLongIdInputDTO;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;
import org.github.zuuuyao.common.validate.group.Group;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-16 19:41
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SetRoleInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = -2652755823180838443L;

    @Schema(description = "用户id", example = "12684")
    @ValidateNotNullAndEmpty(message = "用户id不能为空")
    private Long userId;

    /**
     * 角色id
     */
    @Schema(description = "角色id集合", example = "[1]")
    @ValidateNotNullAndEmpty(message = "角色不能为空")
    private ArrayList<Long> roleIds;

}
