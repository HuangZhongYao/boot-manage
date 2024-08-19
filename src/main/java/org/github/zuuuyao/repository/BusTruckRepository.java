package org.github.zuuuyao.repository;

import org.github.zuuuyao.config.mybatis.extension.BaseMapperExtension;
import org.springframework.stereotype.Repository;
import org.github.zuuuyao.entity.bustruck.BusTruckEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
/**
 * 车辆表仓储层
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 20:23:37
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Repository
public interface BusTruckRepository extends BaseMapperExtension<BusTruckEntity> {

}
