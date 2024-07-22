package org.github.zuuuyao.service.role.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.entity.system.RoleEntity;
import org.github.zuuuyao.entity.system.RoleResourcesEntity;
import org.github.zuuuyao.entity.system.UserEntity;
import org.github.zuuuyao.entity.system.UserRoleEntity;
import org.github.zuuuyao.repository.RoleRepository;
import org.github.zuuuyao.repository.RoleResourcesRepository;
import org.github.zuuuyao.repository.UserRepository;
import org.github.zuuuyao.repository.UserRoleRepository;
import org.github.zuuuyao.service.role.IRoleService;
import org.github.zuuuyao.service.role.dto.input.AddRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.EditRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.RolePageQueryInputDTO;
import org.github.zuuuyao.service.role.dto.input.SetRoleUserInputDTO;
import org.github.zuuuyao.service.role.dto.model.RoleUserModel;
import org.github.zuuuyao.service.role.dto.output.RolePageQueryListItemVo;
import org.github.zuuuyao.service.role.dto.output.RoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

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
    UserRoleRepository userRoleRepository;
    UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delRole(BaseManyLongIdInputDTO inputDTO) {

        // 清除角色与权限中间表数据
        roleResourcesRepository.delete(Wrappers
                .<RoleResourcesEntity>lambdaQuery()
                .in(RoleResourcesEntity::getRoleId, inputDTO.getIds()));
        // 清除角色与用户中间表数据
        userRoleRepository.delete(Wrappers
                .<UserRoleEntity>lambdaQuery()
                .in(UserRoleEntity::getRoleId, inputDTO.getIds()));
        // 删除角色
        roleRepository.deleteByIds(inputDTO.getIds());

        return true;
    }

    @Override
    public Page<RolePageQueryListItemVo> pageQueryList(RolePageQueryInputDTO inputDTO) {

        // 执行查询
        Page<RolePageQueryListItemVo> result =
                this.roleRepository.pageQueryList(inputDTO.toMybatisPageObject(), inputDTO);

        // 设置权限id集合
        result.getRecords().forEach(record -> {
            // 权限id字符串用逗号拼接的
            String resourcesIds = record.getResourcesIds();
            if (StrUtil.isNotBlank(resourcesIds)) {
                // 将字符串ids转为long集合
                List<Long> permissionIds =
                        Stream.of(resourcesIds.split(",")).map(Long::valueOf).toList();
                // 赋值
                record.getPermissionIds().addAll(permissionIds);
            }
        });

        return result;
    }

    @Override
    public List<RoleVo> queryList(Boolean enable) {
        return roleRepository.selectList(
                Wrappers.<RoleEntity>lambdaQuery().eq(null != enable, RoleEntity::getEnable, enable),
                RoleVo.class);
    }


    @Override
    public List<RoleUserModel> queryRoleUserList(Long id) {
        // 查询角色下的用户 用户id
        List<Long> userIds = userRoleRepository.selectList(Wrappers.<UserRoleEntity>lambdaQuery()
                        .select(UserRoleEntity::getUserId)
                        .eq(UserRoleEntity::getRoleId, id))
                .stream()
                .map(UserRoleEntity::getUserId)
                .toList();

        if (userIds.isEmpty()) {
            return new ArrayList<>();
        }

        // 查询用户信息
        return userRepository.selectList(
                Wrappers.<UserEntity>lambdaQuery().in(UserEntity::getId, userIds),
                RoleUserModel.class);
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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean setRoleUser(SetRoleUserInputDTO inputDTO) {
        RoleEntity roleEntity = this.roleRepository.selectById(inputDTO.getRoleId());

        if (null == roleEntity) {
            throw new UserFriendlyException("该角色不存在");
        }

        if (CollectionUtil.isEmpty(inputDTO.getUserIds())) {
            return true;
        }

        // 已经存在用户
        List<Long> alreadyExists =
                userRoleRepository.selectList(Wrappers.<UserRoleEntity>lambdaQuery()
                                .eq(UserRoleEntity::getRoleId, inputDTO.getRoleId()))
                        .stream()
                        .map(UserRoleEntity::getUserId)
                        .toList();

        // 新增用户id
        Collection<Long> addUser = CollectionUtil.subtract(inputDTO.getUserIds(), alreadyExists);
        // 新增用户id集合转换为UserRoleEntity集合
        List<UserRoleEntity> addUserRoleEntityList = addUser.stream()
                .map(userId ->
                        UserRoleEntity
                                .builder()
                                .roleId(inputDTO.getRoleId())
                                .userId(userId)
                                .build())
                .toList();
        // 执行新增
        userRoleRepository.insert(addUserRoleEntityList, addUserRoleEntityList.size());

        // 移除用户id
        Collection<Long> removeUser = CollectionUtil.subtract(alreadyExists, inputDTO.getUserIds());
        // 执行移除
        if (!removeUser.isEmpty()) {
            userRoleRepository.delete(Wrappers.<UserRoleEntity>lambdaQuery()
                    .eq(UserRoleEntity::getRoleId, inputDTO.getRoleId())
                    .in(UserRoleEntity::getUserId, removeUser));
        }

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
