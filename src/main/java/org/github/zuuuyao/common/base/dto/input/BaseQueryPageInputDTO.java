package org.github.zuuuyao.common.base.dto.input;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.github.zuuuyao.common.base.dto.BaseDTO;

/**
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
    private static final long MIN_PAGE_NO = 1L;

    @Schema(name = "pageSize", description = "每页显示条数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private long pageSize = MIN_SIZE;

    @Schema(name = "pageNo", description = "当前页码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private long pageNo = MIN_PAGE_NO;

    public BaseQueryPageInputDTO() {
    }

    public BaseQueryPageInputDTO(long current, long pageSize) {
        if (current > MIN_PAGE_NO) {
            this.pageNo = current;
        }

        if (pageSize > MIN_SIZE) {
            this.pageSize = pageSize;
        }

    }

    /**
     * 转换为mybatis-plus分页查询对象
     *
     * @return Page mybatis分页查询对象
     */
    public Page toMybatisPageObject() {
        return new Page(this.pageNo, this.pageSize);
    }

}
