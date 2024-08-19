package org.github.zuuuyao.service.bustruck.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.repository.BusTruckRepository;
import org.github.zuuuyao.entity.bustruck.BusTruckEntity;
import org.github.zuuuyao.service.bustruck.IBusTruckService;
import org.github.zuuuyao.service.bustruck.dto.input.AddBusTruckInputDTO;
import org.github.zuuuyao.service.bustruck.dto.input.EditBusTruckInputDTO;
import org.github.zuuuyao.service.bustruck.dto.input.BusTruckQueryPageInputDTO;
import org.github.zuuuyao.service.bustruck.dto.output.BusTruckVO;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
/**
 * 车辆表Service服务接口实现层
 * @Desc Created by Velocity Generate.
 * @Time 2024-07-31 20:23:37
 * @Author zuuuYao (https://github.com/HuangZhongYao)
 */
@Service
@AllArgsConstructor
public class IBusTruckServiceImpl implements IBusTruckService {

    @Resource
    BusTruckRepository busTruckRepository;

    @Override
    public Page<BusTruckVO> pageQueryList(BusTruckQueryPageInputDTO inputDTO) {

        // 构建查询条件
        LambdaQueryWrapper<BusTruckEntity> queryWrapper = Wrappers.<BusTruckEntity>lambdaQuery();

        // 执行查询用户
        Page<BusTruckVO> page = busTruckRepository.selectPage(inputDTO.toMybatisPageObject(), queryWrapper, BusTruckVO.class);

        return page;
    }


    @Override
    public Boolean delBusTruck(BaseManyLongIdInputDTO inputDTO) {

        // 删除用户
        busTruckRepository.deleteByIds(inputDTO.getIds());
        return true;
    }

    @Override
    public Boolean addBusTruck(AddBusTruckInputDTO inputDTO) {

        // 将DTO转换为实体对象
        BusTruckEntity addEntity = ModelMapperUtil.map(inputDTO, BusTruckEntity.class);
        // 插入数据库
        busTruckRepository.insert(addEntity);

        return true;
    }

    @Override
    public Boolean editBusTruck(EditBusTruckInputDTO inputDTO) {

        // 更新的数据
        BusTruckEntity updateEntity = ModelMapperUtil.map(inputDTO, BusTruckEntity.class);

        // 执行更新
        busTruckRepository.updateById(updateEntity);

        return true;
    }

}
