package org.github.zuuuyao.entity.bustruck;

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
 * 车辆表实体
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 20:23:37
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("bus_truck")
public class BusTruckEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -1;

        /**
     * 品牌
     */
    private String brand;

        /**
     * 发动机编号
     */
    private String engineNo;

        /**
     * 文档编号
     */
    private String fileNo;

        /**
     * 发证机关
     */
    private String issuers;

        /**
     * 型号
     */
    private String model;

        /**
     * 车辆所有人
     */
    private String owner;

        /**
     * 车牌号
     */
    private String plateNo;

        /**
     * 总质量kg
     */
    private Integer quality;

        /**
     * 注册日期
     */
    private LocalDateTime regDate;

        /**
     * 使用性质
     */
    private String useCharacter;

        /**
     * 车型
     */
    private String vehicleType;

        /**
     * VIN车辆识别代码
     */
    private String vin;

    }

