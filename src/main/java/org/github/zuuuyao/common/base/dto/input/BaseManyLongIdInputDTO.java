package org.github.zuuuyao.common.base.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import org.github.zuuuyao.common.base.dto.BaseDTO;

import java.io.Serial;
import java.util.ArrayList;

/**
 * 多个Long类型 id集合 DTO基类
 * @Desc
 * @Time 2024-07-12 10:31
 * @Author HuangZhongYao
 */
public class BaseManyLongIdInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 7165155487737319430L;

    public BaseManyLongIdInputDTO() {
    }

    public BaseManyLongIdInputDTO(ArrayList<Long> ids) {
        this.ids = ids;
    }

    /**
     * ids
     */
    @Schema(name = "id集合字段",required = true,example = "[1092327965422,12398293009,1092323556]")
    private ArrayList<Long> ids = new ArrayList<>();

    public ArrayList<Long> getIds() {
        return ids;
    }

    public void setIds(ArrayList<Long> ids) {
        this.ids = ids;
    }
}
