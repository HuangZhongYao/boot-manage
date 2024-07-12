package org.github.zuuuyao.common.base.dto.input;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.github.zuuuyao.common.base.dto.BaseDTO;

import java.io.Serial;

/**
 *
 * @Desc 分页查询DTO基类
 * @Time 2024-07-11 14:49
 * @Author HuangZhongYao
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseQueryPageInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = -2789045699043909727L;

    /**
     * 最小查询条数
     */
    private static final long MIN_SIZE = 10L;

    /**
     * 最小查询页数
     */
    private static final long MIN_CURRENT = 1L;

    @Schema(
            name = "每页显示条数",
            required = true, example = "10"
    )
    private long size = MIN_SIZE;

    @Schema(
            name = "当前页码",
            required = true, example = "1"
    )
    private long current = MIN_CURRENT;

    public BaseQueryPageInputDTO() {
    }

    public BaseQueryPageInputDTO(long current, long size) {
        if (current > MIN_CURRENT) {
            this.current = current;
        }

        if (size > MIN_SIZE) {
            this.size = size;
        }

    }

    /**
     * 转换为mybatis-plus分页查询对象
     *
     * @return Page mybatis分页查询对象
     */
    public Page toMybatisPageObject() {
        return new Page(this.current, this.size);
    }

}
