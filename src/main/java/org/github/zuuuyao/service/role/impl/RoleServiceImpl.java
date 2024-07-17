package org.github.zuuuyao.service.role.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.entity.system.RoleEntity;
import org.github.zuuuyao.entity.system.RoleResourcesEntity;
import org.github.zuuuyao.repository.RoleRepository;
import org.github.zuuuyao.repository.RoleResourcesRepository;
import org.github.zuuuyao.service.role.IRoleService;
import org.github.zuuuyao.service.role.dto.input.AddRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.EditRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.RolePageQueryInputDTO;
import org.github.zuuuyao.service.role.dto.output.RoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    RoleResourcesRepository roleResourcesRepository;

    @Override
    public Boolean delRole(BaseManyLongIdInputDTO inputDTO) {
        roleRepository.deleteByIds(inputDTO.getIds());
        return true;
    }

    @Override
    public Page<RoleVo> pageQueryList(RolePageQueryInputDTO inputDTO) {
        return roleRepository.selectPage(inputDTO.toMybatisPageObject(),
                Wrappers.<RoleEntity>lambdaQuery()
                        .like(StrUtil.isNotBlank(inputDTO.getName()), RoleEntity::getName,
                                inputDTO.getName())
                        .eq(null != inputDTO.getEnable(), RoleEntity::getEnable, inputDTO.getEnable()),
                RoleVo.class);
    }

    @Override
    public List<RoleVo> queryList(Boolean enable) {
        return roleRepository.selectList(
                Wrappers.<RoleEntity>lambdaQuery().eq(RoleEntity::getEnable, enable), RoleVo.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean editRole(EditRoleInputDTO inputDTO) {
        // 判断该角色id是否有效
        if (!roleRepository.exists(
                Wrappers.<RoleEntity>lambdaQuery().eq(RoleEntity::getId, inputDTO.getId()))) {
            throw new UserFriendlyException("该角色不存在");
        }

        // 更新权限
        if (inputDTO.getPermissionIds() != null) {

            // 先清空角色权限
            roleResourcesRepository.delete(Wrappers
                    .<RoleResourcesEntity>lambdaQuery()
                    .eq(RoleResourcesEntity::getRoleId, inputDTO.getId()));

            // 使用流创建UserRoleEntity对象
            List<RoleResourcesEntity> roleResourcesEntities = inputDTO.getPermissionIds()
                    .stream()
                    .map(permissionId -> RoleResourcesEntity.builder()
                            .roleId(inputDTO.getId())
                            .resourcesId(permissionId)
                            .build())
                    .toList();

            // 批量插入角色权限数据
            roleResourcesRepository.insert(roleResourcesEntities, roleResourcesEntities.size());
        }

        // 更新角色的数据
        RoleEntity updateEntity = ModelMapperUtil.map(inputDTO, RoleEntity.class);

        // 执行更新
        roleRepository.updateById(updateEntity);

        return true;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addRole(AddRoleInputDTO inputDTO) {
        // 判断角色名是否重复
        if (roleRepository.exists(
                Wrappers.<RoleEntity>lambdaQuery().eq(RoleEntity::getName, inputDTO.getName()))) {
            throw new UserFriendlyException("该角色已存在");
        }

        // 判断角色编码是否重复
        if (roleRepository.exists(
                Wrappers.<RoleEntity>lambdaQuery().eq(RoleEntity::getCode, inputDTO.getCode()))) {
            throw new UserFriendlyException("该角色编码已存在");
        }

        // 将DTO转换为实体对象
        RoleEntity roleEntity = ModelMapperUtil.map(inputDTO, RoleEntity.class);
        // 插入数据库
        roleRepository.insert(roleEntity);

        // 如果勾选了权限就插入权限数据
        if (inputDTO.getPermissionIds() != null) {

            // 使用流创建UserRoleEntity对象
            List<RoleResourcesEntity> roleResourcesEntities = inputDTO.getPermissionIds()
                    .stream()
                    .map(permissionId -> RoleResourcesEntity.builder()
                            .roleId(roleEntity.getId())
                            .resourcesId(permissionId)
                            .build())
                    .toList();

            // 批量插入角色权限数据
            roleResourcesRepository.insert(roleResourcesEntities, roleResourcesEntities.size());
        }
        return true;
    }
}
