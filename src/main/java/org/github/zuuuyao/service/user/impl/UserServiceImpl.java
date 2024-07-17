package org.github.zuuuyao.service.user.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.entity.system.RoleResourcesEntity;
import org.github.zuuuyao.entity.system.UserEntity;
import org.github.zuuuyao.entity.system.UserRoleEntity;
import org.github.zuuuyao.repository.UserRepository;
import org.github.zuuuyao.repository.UserRoleRepository;
import org.github.zuuuyao.service.user.IUserService;
import org.github.zuuuyao.service.user.dto.input.AddUserInputDTO;
import org.github.zuuuyao.service.user.dto.input.EditUserInputDTO;
import org.github.zuuuyao.service.user.dto.input.ResetPasswordInputDTO;
import org.github.zuuuyao.service.user.dto.input.SetRoleInputDTO;
import org.github.zuuuyao.service.user.dto.input.UserQueryPageInputDTO;
import org.github.zuuuyao.service.user.dto.output.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Desc
 * @Time 2024-07-11 16:33
 * @Author HuangZhongYao
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserRepository userRepository;
    @Resource
    UserRoleRepository userRoleRepository;

    @Override
    public Page<UserVo> pageQueryList(UserQueryPageInputDTO inputDTO) {

        // 构建查询条件
        LambdaQueryWrapper<UserEntity> queryWrapper = Wrappers.<UserEntity>lambdaQuery()
            .like(StrUtil.isNotBlank(inputDTO.getUsername()), UserEntity::getAccount,
                inputDTO.getAccount())
            .like(StrUtil.isNotBlank(inputDTO.getAccount()), UserEntity::getAccount,
                inputDTO.getUsername())
            .eq(null != inputDTO.getGender(), UserEntity::getGender, inputDTO.getGender())
            .eq(null != inputDTO.getEnable(), UserEntity::getEnable, inputDTO.getEnable());

        return userRepository.selectPage(inputDTO.toMybatisPageObject(), queryWrapper,
            UserVo.class);
    }

    @Override
    public Boolean delUser(BaseManyLongIdInputDTO inputDTO) {

        // 删除用户角色中间表数据
        userRoleRepository.delete(Wrappers
                .<UserRoleEntity>lambdaQuery()
                .in(UserRoleEntity::getUserId, inputDTO.getIds()));

        // 删除用户
        userRepository.deleteByIds(inputDTO.getIds());
        return true;
    }

    @Override
    public Boolean addUser(AddUserInputDTO inputDTO) {

        // 判断账号是否已存在
        if (userRepository.exists(
            Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getAccount, inputDTO.getAccount()))) {
            throw new UserFriendlyException("账号已存在", 411);
        }

        // 将DTO转换为实体对象
        UserEntity userEntity = ModelMapperUtil.map(inputDTO, UserEntity.class);

        // 插入数据库
        userRepository.insert(userEntity);
        return true;
    }

    @Override
    public Boolean resetPassword(ResetPasswordInputDTO inputDTO) {

        // 修改条件
        LambdaQueryWrapper<UserEntity> wrapper = Wrappers
            .<UserEntity>lambdaQuery()
            .eq(UserEntity::getId, inputDTO.getId())
            .last(" limit 1 ");

        UserEntity userEntity = UserEntity
            .builder()
            .password(inputDTO.getPassword())
            .build();

        // 执行更新
        int result = this.userRepository.update(userEntity, wrapper);

        if (result == 0) {
            throw new UserFriendlyException("修改密码失败");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean setRole(SetRoleInputDTO inputDTO) {

        // 先清空角色
        userRoleRepository.delete(Wrappers
            .<UserRoleEntity>lambdaQuery()
            .eq(UserRoleEntity::getUserId, inputDTO.getUserId()));

        // 使用流创建UserRoleEntity对象
        List<UserRoleEntity> userRoleEntities = inputDTO.getRoleIds()
            .stream()
            .map(roleId -> UserRoleEntity.builder()
                .roleId(roleId)
                .userId(inputDTO.getUserId())
                .build())
            .toList();

        // 批量插入
        userRoleRepository.insert(userRoleEntities, userRoleEntities.size());

        return true;
    }

    @Override
    public Boolean editUser(EditUserInputDTO inputDTO) {
        // 判断该用户id是否有效
        if (!userRepository.exists(
            Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getId, inputDTO.getId()))) {
            throw new UserFriendlyException("该用户不存在");
        }

        // 更新的数据
        UserEntity updateEntity = ModelMapperUtil.map(inputDTO, UserEntity.class);

        // 执行更新
        userRepository.updateById(updateEntity);

        return true;
    }
}
