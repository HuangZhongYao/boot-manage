package org.github.zuuuyao.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.service.order.IBusOrderService;
import org.github.zuuuyao.service.order.dto.input.AddBusOrderInputDTO;
import org.github.zuuuyao.service.order.dto.input.EditBusOrderInputDTO;
import org.github.zuuuyao.service.order.dto.input.BusOrderQueryPageInputDTO;
import org.github.zuuuyao.service.order.dto.output.BusOrderVO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
/**
 * 商城订单表Controller层,接受处理请求
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 10:03:05
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Tag(name = "商城订单接口")
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class BusOrderController {

    @Resource
    IBusOrderService busOrderService;

    @Operation(summary = "分页查询", description = "分页查询商城订单接口}")
    @GetMapping(value = "/pageQueryList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiOperationSupport(authors = "zuuuYao")
    public Page<BusOrderVO> pageQueryList(BusOrderQueryPageInputDTO inputDTO) {
        return busOrderService.pageQueryList(inputDTO);
    }

    @Operation(summary = "添加商城订单", description = "添加商城订单接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping(value = "/addBusOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addBusOrder(@RequestBody @Validated AddBusOrderInputDTO inputDTO) {
        return busOrderService.addBusOrder(inputDTO);
    }

    @Operation(summary = "删除商城订单", description = "根据id删除接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(value = "/delBusOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delBusOrder(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return busOrderService.delBusOrder(inputDTO);
    }

    @Operation(summary = "编辑商城订单", description = "编辑商城订单接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PatchMapping(value = "/editBusOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addBusOrder(@RequestBody @Validated EditBusOrderInputDTO inputDTO) {
        return busOrderService.editBusOrder(inputDTO);
    }
}
