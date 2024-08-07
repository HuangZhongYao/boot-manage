package org.github.zuuuyao.service.auth.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.constant.RoleConstant;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.common.util.RequestUtil;
import org.github.zuuuyao.common.util.UserAgentInfo;
import org.github.zuuuyao.common.util.tree.ITreeNode;
import org.github.zuuuyao.common.util.tree.TreeUtil;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.entity.system.RoleResourcesEntity;
import org.github.zuuuyao.entity.system.UserEntity;
import org.github.zuuuyao.entity.system.UserRoleEntity;
import org.github.zuuuyao.repository.*;
import org.github.zuuuyao.service.auth.IAuthService;
import org.github.zuuuyao.service.auth.dto.AuthenticationUserDetailOutputDTO;
import org.github.zuuuyao.service.auth.dto.LoginInputDTO;
import org.github.zuuuyao.service.auth.dto.LoginOutputDTO;
import org.github.zuuuyao.service.resources.model.ResourcesTreeVo;
import org.github.zuuuyao.service.resources.model.ResourcesVo;
import org.github.zuuuyao.service.role.dto.output.RoleVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserRoleRepository userRoleRepository;
    RoleResourcesRepository roleResourcesRepository;


    @Override
    public List<ResourcesEntity> queryPermissionsList(Long userId) {
        // 如果有超级管理员角色拥有全部权限
        if (StpUtil.getRoleList().contains(RoleConstant.SUPER_ADMIN_CODE)) {
            return resourcesRepository.selectList(null);
        }
        // 用户角色id集合
        List<Long> userRoleIds = userRoleRepository.selectList(Wrappers.<UserRoleEntity>lambdaQuery().eq(UserRoleEntity::getUserId, userId)).stream().map(UserRoleEntity::getRoleId).toList();

        if (userRoleIds.isEmpty()) {
            return new ArrayList<>();
        }

        // 角色的权限id集合
        List<Long> roleResourcesList = roleResourcesRepository.selectList(Wrappers.<RoleResourcesEntity>lambdaQuery().in(RoleResourcesEntity::getRoleId, userRoleIds)).stream().map(RoleResourcesEntity::getResourcesId).toList();

        if (roleResourcesList.isEmpty()) {
            return new ArrayList<>();
        }
        return resourcesRepository.selectList(Wrappers.<ResourcesEntity>lambdaQuery().in(ResourcesEntity::getId, roleResourcesList));
    }

    @Override
    public List<ResourcesTreeVo> queryPermissionsTree() {

        // 当前用户id
        long currentUserId = StpUtil.getLoginIdAsLong();
        // 获取用户权限列表
        List<ResourcesTreeVo> resourcesVos = ModelMapperUtil.mapList(this.queryPermissionsList(currentUserId), ResourcesTreeVo.class);

        // 转换ITreeNode List
        List<ITreeNode<Long>> treeNodeList = new ArrayList<>(resourcesVos.size());
        treeNodeList.addAll(resourcesVos);

        List<ITreeNode<Long>> tree = TreeUtil.listToTree(treeNodeList);
        return ModelMapperUtil.mapList(tree, ResourcesTreeVo.class);
    }

    @Override
    public String captcha() {
        return null;
    }

    @Override
    public LoginOutputDTO login(LoginInputDTO inputDTO, HttpServletRequest request) {

        // 获取用户信息
        UserEntity loginUser = userRepository.selectOne(
                Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getAccount, inputDTO.getAccount()));

        // 判断账号有效性
        if (null == loginUser) {
            throw new UserFriendlyException("账号不存在", 430);
        }

        // 输入的密码
        String inputPwd =
                DigestUtil.sha256Hex(inputDTO.getPassword() + loginUser.getSalt(), CharsetUtil.UTF_8);

        // 比对密码
        if (!StrUtil.equals(inputPwd, loginUser.getPassword())) {
            throw new UserFriendlyException("账号或密码错误", 420);
        }

        // 解析User-Agent的信息
        String header = request.getHeader("User-Agent");
        UserAgentInfo userAgentInfo = RequestUtil.parseUserAgent(header);
        // sa-token 登录 并设置扩展参数, 通过StpUtil.getExtra("user");获取扩展参数
        StpUtil.login(loginUser.getId(), SaLoginConfig
                .setDevice("PC")
                .setExtra("device", userAgentInfo.getOs() + " " + userAgentInfo.getBrowser())
                .setExtra("id", loginUser.getId())
                .setExtra("username", loginUser.getUsername())
                .setExtra("account", loginUser.getAccount())
                .setExtra("phone", loginUser.getPhone())
                .setExtra("user",loginUser)
        );

        // 获取token信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return LoginOutputDTO
                .builder()
                .accessToken(tokenInfo.getTokenValue())
                .id(loginUser.getId())
                .account(loginUser.getAccount())
                .avatarUrl(loginUser.getAvatarUrl())
                .username(loginUser.getUsername())
                .gender(loginUser.getGender())
                .phone(loginUser.getPhone())
                .build();
    }

    @Override
    public void logout() {
        StpUtil.logout(StpUtil.getLoginId(), "PC");
    }

    @Override
    public AuthenticationUserDetailOutputDTO authenticationUserDetail() {
        // 当前用户Id
        long currentUserId = StpUtil.getLoginIdAsLong();
        // 查询当前用户
        AuthenticationUserDetailOutputDTO
                output = ModelMapperUtil.map(userRepository.selectById(currentUserId), AuthenticationUserDetailOutputDTO.class);
        // 用户角色列表
        List<RoleVo> roles = roleRepository.queryUserRolesByUserId(currentUserId);
        // 资源权限列表
        List<ResourcesVo> permissions = ModelMapperUtil.mapList(this.queryPermissionsList(currentUserId), ResourcesVo.class);
        // 组装角色
        output.setRoles(roles);
        // 组装权限
        output.setPermissions(permissions);
        return output;
    }
}
