package org.github.zuuuyao.service.role.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.entity.system.RoleEntity;
import org.github.zuuuyao.repository.RoleRepository;
import org.github.zuuuyao.service.role.IRoleService;
import org.github.zuuuyao.service.role.dto.input.AddRoleInputDTO;
import org.github.zuuuyao.service.role.dto.output.RoleVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 18:40
 */
@AllArgsConstructor
@Service
public class RoleServiceImpl implements IRoleService {

    RoleRepository roleRepository;

    @Override
    public Boolean delRole(BaseManyLongIdInputDTO inputDTO) {
        roleRepository.deleteByIds(inputDTO.getIds());
        return true;
    }

    @Override
    public Page<RoleEntity> pageQueryList(BaseQueryPageInputDTO inputDTO) {
        return roleRepository.selectPage(inputDTO.toMybatisPageObject(), Wrappers.<RoleEntity>lambdaQuery());
    }

    @Override
    public List<RoleVo> queryList(Boolean enable) {
        return roleRepository.selectList(Wrappers.<RoleEntity>lambdaQuery().eq(RoleEntity::getEnable, enable), RoleVo.class);
    }

    @Override
    public boolean addRole(AddRoleInputDTO inputDTO) {
        // 判断角色名是否重复
        if (roleRepository.exists(
                Wrappers.<RoleEntity>lambdaQuery().eq(RoleEntity::getName, inputDTO.getName()))) {
            throw new UserFriendlyException("该角色已存在", 401);
        }

        // 将DTO转换为实体对象
        RoleEntity roleEntity = ModelMapperUtil.map(inputDTO, RoleEntity.class);

        // 插入数据库
        roleRepository.insert(roleEntity);
        return true;
    }
}
