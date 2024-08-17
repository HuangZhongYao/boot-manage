package org.github.zuuuyao.service.dict.output;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.github.zuuuyao.common.base.dto.output.BaseOutputIdAndTimeAndOperationDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.io.Serial;


/**
 * 系统字典类型表VO对象
 *
 * @Desc Created by Velocity Generate.
 * @Time 2024-08-18 05:13:04
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DictTypeVO extends BaseOutputIdAndTimeAndOperationDTO {

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
    @Schema(description = "名称")
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

