package org.github.zuuuyao.common.base.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import org.github.zuuuyao.common.base.dto.BaseDTO;

import java.io.Serial;

/**
 * @Desc baseOutputDTO 基类
 * @Time 2024-07-12 10:41
 * @Author HuangZhongYao
 */
public class BaseOutputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = -8243192684166209735L;


    public BaseOutputDTO() {
    }

    public BaseOutputDTO(String id) {
        this.id = id;
    }

    @Schema(name = "id字段",requiredMode = Schema.RequiredMode.REQUIRED,example = "1092327965422")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
