package org.github.zuuuyao.service.role.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.github.zuuuyao.common.base.dto.output.BaseOutputIdAndTimeDTO;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-15 21:40
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RolePageQueryListItemVo extends BaseOutputIdAndTimeDTO {

    @Serial
    private static final long serialVersionUID = 2264364149067433513L;

    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 启用状态
     */
    @Schema(description = "启用状态")
    private Boolean enable;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String code;

    /**
     *
     */
    @Schema(description = "权限id集合")
    private List<Long> permissionIds = new ArrayList<>();

    @Schema(description = "权限id字符串拼接逗号分割")
    private String resourcesIds;
}
