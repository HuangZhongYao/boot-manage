package org.github.zuuuyao.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.service.bustruck.IBusTruckService;
import org.github.zuuuyao.service.bustruck.dto.input.AddBusTruckInputDTO;
import org.github.zuuuyao.service.bustruck.dto.input.EditBusTruckInputDTO;
import org.github.zuuuyao.service.bustruck.dto.input.BusTruckQueryPageInputDTO;
import org.github.zuuuyao.service.bustruck.dto.output.BusTruckVO;
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
 * 车辆表Controller层,接受处理请求
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 20:23:37
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Tag(name = "车辆接口")
@RestController
@RequestMapping("/bustruck")
@AllArgsConstructor
public class BusTruckController {

    @Resource
    IBusTruckService busTruckService;

    @Operation(summary = "分页查询", description = "分页查询车辆接口}")
    @GetMapping(value = "/pageQueryList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiOperationSupport(authors = "zuuuYao")
    public Page<BusTruckVO> pageQueryList(BusTruckQueryPageInputDTO inputDTO) {
        return busTruckService.pageQueryList(inputDTO);
    }

    @Operation(summary = "添加车辆", description = "添加车辆接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping(value = "/addBusTruck", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addBusTruck(@RequestBody @Validated AddBusTruckInputDTO inputDTO) {
        return busTruckService.addBusTruck(inputDTO);
    }

    @Operation(summary = "删除车辆", description = "根据id删除接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(value = "/delBusTruck", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delBusTruck(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return busTruckService.delBusTruck(inputDTO);
    }

    @Operation(summary = "编辑车辆", description = "编辑车辆接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PatchMapping(value = "/editBusTruck", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addBusTruck(@RequestBody @Validated EditBusTruckInputDTO inputDTO) {
        return busTruckService.editBusTruck(inputDTO);
    }
}
