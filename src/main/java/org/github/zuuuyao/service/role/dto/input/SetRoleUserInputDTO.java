package org.github.zuuuyao.service.role.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.BaseDTO;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-21 16:56
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SetRoleUserInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 3151865599208537313L;

    @Schema(description = "角色Id")
    public Long roleId;

    @Schema(description = "用户Id集合")
    public Set<Long> userIds;
}
