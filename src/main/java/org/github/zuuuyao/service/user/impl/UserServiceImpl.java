package org.github.zuuuyao.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.entity.system.UserEntity;
import org.github.zuuuyao.repository.UserRepository;
import org.github.zuuuyao.service.user.IUserService;
import org.github.zuuuyao.service.user.dto.input.AddUserInputDTO;
import org.springframework.stereotype.Service;

/**
 * @Desc
 * @Time 2024-07-11 16:33
 * @Author HuangZhongYao
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserRepository userRepository;

    @Override
    public Object pageQueryList(BaseQueryPageInputDTO inputDTO) {
        return userRepository.selectPage(inputDTO.toMybatisPageObject(), new QueryWrapper<UserEntity>());
    }

    @Override
    public Object addUser(AddUserInputDTO inputDTO) {

        // 判断账号是否已存在
        if (userRepository.selectCount(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getAccount, inputDTO.getAccount())) > 0) {
            return "401";
        }

        // 将DTO转换为实体对象
        UserEntity userEntity = ModelMapperUtil.map(inputDTO, UserEntity.class);

        // 插入数据库
        return userRepository.insert(userEntity);
    }
}
