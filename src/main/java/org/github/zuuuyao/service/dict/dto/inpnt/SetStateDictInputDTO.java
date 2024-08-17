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
 * @Time 2024-08-18 4:17
 */
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SetStateDictInputDTO extends BaseLongIdInputDTO {

    @Serial
    private static final long serialVersionUID = 2987177555850281051L;

    @ValidateNotNullAndEmpty(message = "启用状态不能为空")
    @Schema(description = "启用状态: true = 启用, false = 禁用",example = "true")
    private Boolean state;
}
