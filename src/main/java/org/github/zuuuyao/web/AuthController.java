package org.github.zuuuyao.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.web.BaseController;
import org.github.zuuuyao.service.auth.IAuthService;
import org.github.zuuuyao.service.auth.model.ResourcesTreeVo;
import org.github.zuuuyao.service.auth.model.ResourcesVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Operation(summary = "查询用户权限树", description = "查询用户权限,已树形结构返回")
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
}
