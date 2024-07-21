package org.github.zuuuyao.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.web.BaseController;
import org.github.zuuuyao.service.role.IRoleService;
import org.github.zuuuyao.service.role.dto.input.AddRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.EditRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.RolePageQueryInputDTO;
import org.github.zuuuyao.service.role.dto.input.SetRoleUserInputDTO;
import org.github.zuuuyao.service.role.dto.model.RoleUserModel;
import org.github.zuuuyao.service.role.dto.output.RolePageQueryListItemVo;
import org.github.zuuuyao.service.role.dto.output.RoleVo;
import org.github.zuuuyao.service.user.dto.output.UserVo;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 18:41
 */
@Tag(name = "角色接口")
@AllArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    IRoleService roleService;

    @Operation(summary = "分页查询", description = "分页查询角色接口")
    @GetMapping(value = "/pageQueryList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperationSupport(authors = "zuuuYao")
    public Page<RolePageQueryListItemVo> pageQueryList(RolePageQueryInputDTO inputDTO) {
        return roleService.pageQueryList(inputDTO);
    }

    @Operation(summary = "根据状态查询角色", description = "根据角色启用状态查询")
    @GetMapping(value = "/queryList", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters({
        @Parameter(name = "enable", description = "true|false|不传递,true查询启用,false=查询禁用,不传入=查询全部"),
    })
    @ApiOperationSupport(authors = "zuuuYao")
    public List<RoleVo> queryList(Boolean enable) {
        return roleService.queryList(enable);
    }

    @Operation(summary = "查询角色下的用户", description = "查询指定角色下有哪些用户")
    @GetMapping(value = "/queryRoleUserList", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters({
        @Parameter(name = "id", description = "角色id"),
    })
    @ApiOperationSupport(authors = "zuuuYao")
    public List<RoleUserModel> queryRoleUserList(@RequestParam(name = "id") Long id) {
        return roleService.queryRoleUserList(id);
    }

    @Operation(summary = "角色设置用户", description = "批量给角色设置用户")
    @ApiResponse(responseCode = "200", description = "ok")
    @PostMapping("/setRoleUser")
    public Boolean setRoleUser(@RequestBody @Validated SetRoleUserInputDTO inputDTO) {
        return roleService.setRoleUser(inputDTO);
    }

    @Operation(summary = "添加角色")
    @ApiResponse(responseCode = "200", description = "ok")
    @PostMapping("/addRole")
    public Boolean addRole(@RequestBody @Validated AddRoleInputDTO inputDTO) {
        return roleService.addRole(inputDTO);
    }

    @Operation(summary = "编辑角色")
    @ApiResponse(responseCode = "200", description = "ok")
    @PatchMapping("/editRole")
    public Boolean editRole(@RequestBody @Validated EditRoleInputDTO inputDTO) {
        return roleService.editRole(inputDTO);
    }

    @Operation(summary = "删除角色")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(value = "/delRole", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delRole(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return roleService.delRole(inputDTO);
    }

}
