package org.github.zuuuyao.service.order.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
/**
 * 商城订单表分页查询DTO对象
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 10:03:05
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BusOrderQueryPageInputDTO extends BaseQueryPageInputDTO {

    @Serial
    private static final long serialVersionUID = -1;

    /**
     * 查询关键词
     */
    @Schema(description = "查询关键词")
    private String keywords;

}
