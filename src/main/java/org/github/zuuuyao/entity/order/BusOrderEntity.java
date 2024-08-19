package org.github.zuuuyao.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.github.zuuuyao.common.base.entity.AbstractBaseEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.io.Serial;


/**
 * 商城订单表实体
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
@TableName("bus_order")
public class BusOrderEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -1;

        /**
     * 订单号
     */
    private String orderNo;

        /**
     * 商品
     */
    private String commodity;

        /**
     * 下单时间
     */
    private LocalDateTime orderTime;

        /**
     * 收货地址
     */
    private String address;

        /**
     * 收货人
     */
    private String consignee;

        /**
     * 收货人电话
     */
    private String consigneePhone;

        /**
     * 支付状态
     */
    private Byte payState;

    }

