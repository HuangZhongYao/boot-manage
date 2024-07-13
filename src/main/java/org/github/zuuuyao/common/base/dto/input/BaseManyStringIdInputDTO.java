package org.github.zuuuyao.common.base.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import org.github.zuuuyao.common.base.dto.BaseDTO;

import java.io.Serial;
import java.util.ArrayList;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;

/**
 * 多个String类型 id集合 DTO基类
 * @Desc
 * @Time 2024-07-12 10:31
 * @Author HuangZhongYao
 */
public class BaseManyStringIdInputDTO extends BaseDTO {


    @Serial
    private static final long serialVersionUID = -5622172719447607907L;

    public BaseManyStringIdInputDTO() {
    }

    public BaseManyStringIdInputDTO(ArrayList<String> ids) {
        this.ids = ids;
    }

    /**
     * ids
     */
    @ValidateNotNullAndEmpty(message = "id集合不能为空")
    @Schema(name = "ids",required = true,example = "['1092327965422','12398293009','1092323556']")
    private ArrayList<String> ids = new ArrayList<>();

    public ArrayList<String> getIds() {
        return ids;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }
}
