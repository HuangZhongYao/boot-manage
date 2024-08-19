package org.github.zuuuyao.service.bustruck;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.service.bustruck.dto.input.AddBusTruckInputDTO;
import org.github.zuuuyao.service.bustruck.dto.input.EditBusTruckInputDTO;
import org.github.zuuuyao.service.bustruck.dto.input.BusTruckQueryPageInputDTO;
import org.github.zuuuyao.service.bustruck.dto.output.BusTruckVO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
/**
 * 车辆表Service服务接口层
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 20:23:37
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
public interface IBusTruckService {

    Boolean addBusTruck(AddBusTruckInputDTO inputDTO);

    Boolean delBusTruck(BaseManyLongIdInputDTO inputDTO);

    Boolean editBusTruck(EditBusTruckInputDTO inputDTO);

    Page<BusTruckVO> pageQueryList(BusTruckQueryPageInputDTO inputDTO);
}
