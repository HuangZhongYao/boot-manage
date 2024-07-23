package org.github.zuuuyao.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.web.BaseController;
import org.github.zuuuyao.service.auth.IAuthService;
import org.github.zuuuyao.service.auth.dto.AuthenticationUserDetailOutputDTO;
import org.github.zuuuyao.service.auth.dto.LoginInputDTO;
import org.github.zuuuyao.service.auth.dto.LoginOutputDTO;
import org.github.zuuuyao.service.resources.model.ResourcesTreeVo;
import org.github.zuuuyao.service.resources.model.ResourcesVo;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc
 * @Time 2024-07-15 15:01
 * @Author HuangZhongYao
 */
@Tag(name = "认证接口", description = "认证相关接口,登录、注销、获取用户权限等")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController extends BaseController {

    IAuthService authService;

    @GetMapping(value = "/queryPermissionsList", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "查询用户权限列表", description = "查询用户的权限列表")
    @ApiResponse(responseCode = "200", description = "ok")
    public List<ResourcesVo> queryPermissionsList() {
        return authService.queryPermissionsList();
    }

    @GetMapping(value = "/queryPermissionsTree", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "查询用户权限树", description = "查询用户权限,以树形结构返回")
    @ApiResponse(responseCode = "200", description = "ok")
    public List<ResourcesTreeVo> queryPermissionsTree() {
        return authService.queryPermissionsTree();
    }

    @Operation(summary = "获取验证码", description = "获取验证码返回SVG图片")
    @ApiResponse(responseCode = "200", description = "ok")
    @GetMapping(value = "/captcha", produces = MediaType.TEXT_HTML_VALUE)
    public String captcha() {
        return authService.captcha();
    }

    @Operation(summary = "登录接口", description = "登录接口")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "420", description = "账号或密码错误")
    @ApiResponse(responseCode = "430", description = "验证码错误")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginOutputDTO login(@Validated @RequestBody LoginInputDTO inputDTO,
                                HttpServletRequest request) {
        return authService.login(inputDTO, request);
    }

    @Operation(summary = "注销登录", description = "注销登录接口")
    @ApiResponse(responseCode = "200", description = "ok")
    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public void logout() {
        authService.logout();
    }

    @Operation(summary = "获取用户详情", description = "获取用户详情")
    @ApiResponse(responseCode = "200", description = "ok")
    @GetMapping(value = "/authenticationUserDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationUserDetailOutputDTO authenticationUserDetail() {
        return authService.authenticationUserDetail();
    }
}
