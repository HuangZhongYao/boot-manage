package org.github.zuuuyao.service.auth.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.repository.ResourcesRepository;
import org.github.zuuuyao.repository.RoleRepository;
import org.github.zuuuyao.repository.UserRepository;
import org.github.zuuuyao.service.auth.IAuthService;
import org.github.zuuuyao.service.auth.model.ResourcesTreeVo;
import org.github.zuuuyao.service.auth.model.ResourcesVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc
 * @Time 2024-07-15 15:03
 * @Author HuangZhongYao
 */
@AllArgsConstructor
@Service
public class AuthServiceImpl implements IAuthService {

    ResourcesRepository resourcesRepository;
    UserRepository repository;
    RoleRepository roleRepository;


    @Override
    public List<ResourcesVo> queryPermissionsList() {
        return resourcesRepository.selectList(Wrappers.<ResourcesEntity>lambdaQuery(), ResourcesVo.class);
    }

    @Override
    public List<ResourcesTreeVo> queryPermissionsTree() {
        return List.of();
    }
}
