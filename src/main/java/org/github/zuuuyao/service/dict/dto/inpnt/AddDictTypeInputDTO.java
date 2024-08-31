package org.github.zuuuyao.service.dict.dto.inpnt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import java.io.Serial;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;

/**
 * 系统字典类型表新增数据DTO对象
 *
 * @Desc Created by Velocity Generate.
 * @Time 2024-08-18 05:13:04
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AddDictTypeInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = -1;


    /**
     * 上级
     */
    @Schema(description = "上级")
    private Long parentId;

    /**
     * 名称
     */
    @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    @ValidateNotNullAndEmpty(message = "字典类型名称不能为空")
    private String name;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    private Integer sort;

    /**
     * 启用状态
     */
    @Schema(description = "启用状态")
    @ValidateNotNullAndEmpty(message = "启用状态不能为空")
    private Boolean enable;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
