package org.github.zuuuyao.service.order;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.service.order.dto.input.AddBusOrderInputDTO;
import org.github.zuuuyao.service.order.dto.input.EditBusOrderInputDTO;
import org.github.zuuuyao.service.order.dto.input.BusOrderQueryPageInputDTO;
import org.github.zuuuyao.service.order.dto.output.BusOrderVO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
/**
 * 商城订单表Service服务接口层
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 10:03:05
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
public interface IBusOrderService {

    Boolean addBusOrder(AddBusOrderInputDTO inputDTO);

    Boolean delBusOrder(BaseManyLongIdInputDTO inputDTO);

    Boolean editBusOrder(EditBusOrderInputDTO inputDTO);

    Page<BusOrderVO> pageQueryList(BusOrderQueryPageInputDTO inputDTO);
}
