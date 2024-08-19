package org.github.zuuuyao.service.order.dto.output;

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
 * 商城订单表VO对象
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 10:03:05
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BusOrderVO extends BaseOutputIdAndTimeAndOperationDTO {

    @Serial
    private static final long serialVersionUID = -1;

        /**
     * 订单号
     */
    @Schema(description = "订单号")
    private String orderNo;

        /**
     * 商品
     */
    @Schema(description = "商品")
    private String commodity;

        /**
     * 下单时间
     */
    @Schema(description = "下单时间")
    private LocalDateTime orderTime;

        /**
     * 收货地址
     */
    @Schema(description = "收货地址")
    private String address;

        /**
     * 收货人
     */
    @Schema(description = "收货人")
    private String consignee;

        /**
     * 收货人电话
     */
    @Schema(description = "收货人电话")
    private String consigneePhone;

        /**
     * 支付状态
     */
    @Schema(description = "支付状态")
    private Byte payState;

    }

