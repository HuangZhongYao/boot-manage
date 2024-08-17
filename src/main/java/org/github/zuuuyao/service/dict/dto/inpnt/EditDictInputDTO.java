package org.github.zuuuyao.service.dict.dto.inpnt;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.input.BaseLongIdInputDTO;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;

/**
 * @Desc Created by IntelliJ IDEA.
 * @Author ZhongYao.Huang (https://github.com/HuangZhongYao)
 * @Copyright ZuuuuYao By Github
 * @Time 2024-08-18 4:18
 */
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class EditDictInputDTO extends BaseLongIdInputDTO {
    @Serial
    private static final long serialVersionUID = -6126750557276017351L;

    /**
     * 字典数据类型id
     */
    @Schema(description = "字典数据类型id")
    @ValidateNotNullAndEmpty(message = "字典数据类型id不能为空")
    private Long dictTypeId;

    /**
     * 名称
     */
    @ValidateNotNullAndEmpty(message = "字典名称不能为空")
    @Schema(description = "名称")
    private String name;

    /**
     * 编码
     */
    @ValidateNotNullAndEmpty(message = "字典编码不能为空")
    @Schema(description = "编码")
    private String code;

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
