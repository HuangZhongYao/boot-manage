package org.github.zuuuyao.repository;

import org.github.zuuuyao.config.mybatis.extension.BaseMapperExtension;
import org.springframework.stereotype.Repository;
import org.github.zuuuyao.entity.order.BusOrderEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
/**
 * 商城订单表仓储层
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 10:03:05
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Repository
public interface BusOrderRepository extends BaseMapperExtension<BusOrderEntity> {

}
