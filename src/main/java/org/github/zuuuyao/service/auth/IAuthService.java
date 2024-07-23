package org.github.zuuuyao.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.github.zuuuyao.service.auth.dto.AuthenticationUserDetailOutputDTO;
import org.github.zuuuyao.service.auth.dto.LoginInputDTO;
import org.github.zuuuyao.service.auth.dto.LoginOutputDTO;
import org.github.zuuuyao.service.resources.model.ResourcesTreeVo;
import org.github.zuuuyao.service.resources.model.ResourcesVo;

import java.util.List;

/**
 * @Desc
 * @Time 2024-07-15 15:02
 * @Author HuangZhongYao
 */
public interface IAuthService {

    List<ResourcesVo> queryPermissionsList();

    List<ResourcesTreeVo> queryPermissionsTree();

    String captcha();

    LoginOutputDTO login(LoginInputDTO inputDTO, HttpServletRequest request);

    AuthenticationUserDetailOutputDTO authenticationUserDetail();

}
