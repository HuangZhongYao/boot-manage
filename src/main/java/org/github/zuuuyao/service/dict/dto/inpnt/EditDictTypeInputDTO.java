package org.github.zuuuyao.service.dict.dto.inpnt;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.input.BaseLongIdInputDTO;

/**
 * 系统字典类型表编辑数据DTO对象
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
public class EditDictTypeInputDTO extends BaseLongIdInputDTO {

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
    private Boolean enable;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
