package org.github.zuuuyao.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.service.auth.dto.AuthenticationUserDetailOutputDTO;
import org.github.zuuuyao.service.auth.dto.LoginInputDTO;
import org.github.zuuuyao.service.auth.dto.LoginOutputDTO;
import org.github.zuuuyao.service.resources.model.ResourcesTreeVo;

import java.util.List;

/**
 * @Desc
 * @Time 2024-07-15 15:02
 * @Author HuangZhongYao
 */
public interface IAuthService {

    /**
     * 获取用户权限列表
     * @param userId 用户id
     * @return 用户权限列表
     */
    List<ResourcesEntity> queryPermissionsList(Long userId);

    List<ResourcesTreeVo> queryPermissionsTree();

    String captcha();

    LoginOutputDTO login(LoginInputDTO inputDTO, HttpServletRequest request);

    void logout();

    AuthenticationUserDetailOutputDTO authenticationUserDetail();

}
