package org.github.zuuuyao.common.base.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import org.github.zuuuyao.common.base.dto.BaseDTO;

import java.io.Serial;

/**
 * String 类型 id DTO基类
 * @Desc
 * @Time 2024-07-12 10:31
 * @Author HuangZhongYao
 */
public class BaseStringIdInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 7875908765778872840L;

    public BaseStringIdInputDTO() {
    }

    public BaseStringIdInputDTO(String id) {
        this.id = id;
    }

    /**
     * id
     */
    @Schema(name = "id字段",required = true,example = "1092327965422")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
