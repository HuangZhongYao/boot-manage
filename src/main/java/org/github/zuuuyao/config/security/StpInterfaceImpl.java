package org.github.zuuuyao.config.security;

import cn.dev33.satoken.stp.StpInterface;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.repository.*;
import org.github.zuuuyao.service.auth.IAuthService;
import org.github.zuuuyao.service.role.dto.output.RoleVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 因为每个项目的需求不同，其权限设计也千变万化，因此 [ 获取当前账号权限码集合 ] 这一操作不可能内置到框架中，
 * 所以 Sa-Token 将此操作以接口的方式暴露给你，以方便你根据自己的业务逻辑进行重写。
 * 你需要做的就是新建一个类，实现 StpInterface接口
 * 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
 *
 * @Desc sa-token 获取用户角色和权限
 * @Time 2024-07-24 11:01
 * @Author HuangZhongYao
 */
@Component
@AllArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    @Resource
    UserRoleRepository userRoleRepository;
    @Resource
    RoleResourcesRepository roleResourcesRepository;
    @Resource
    UserRepository userRepository;
    @Resource
    RoleRepository roleRepository;
    @Resource
    ResourcesRepository resourcesRepository;
    @Resource
    IAuthService authService;

    /**
     * 获取用户权限列表
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 权限编码列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 用户权限集合
        List<ResourcesEntity> permissionsList = this.authService.queryPermissionsList(Long.valueOf(loginId.toString()));
        // 权限编码集合
        return permissionsList.stream().map(ResourcesEntity::getCode).toList();
    }

    /**
     * 获取用户角色列表
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 用户角色编码列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 角色列表
        List<RoleVo> roleList = roleRepository.queryUserRolesByUserId(Long.valueOf(loginId.toString()));
        // 转为角色编码集合
        return roleList.stream().map(RoleVo::getCode).toList();
    }
}
