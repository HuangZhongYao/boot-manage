package org.github.zuuuyao.common.base.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import org.github.zuuuyao.common.base.dto.BaseDTO;

import java.io.Serial;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;

/**
 * Long类型 id DTO基类
 * @Desc
 * @Time 2024-07-12 10:31
 * @Author HuangZhongYao
 */
public class BaseLongIdInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 7875908765778872840L;

    public BaseLongIdInputDTO() {
    }

    public BaseLongIdInputDTO(Long id) {
        this.id = id;
    }

    /**
     * id
     */
    @ValidateNotNullAndEmpty(message = "id 不能为空")
    @Schema(name = "id",requiredMode = Schema.RequiredMode.REQUIRED,example = "1092327965422")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
