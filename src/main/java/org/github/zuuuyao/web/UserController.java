package org.github.zuuuyao.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.validate.group.Group;
import org.github.zuuuyao.service.user.IUserService;
import org.github.zuuuyao.service.user.dto.input.AddUserInputDTO;
import org.github.zuuuyao.service.user.dto.input.ResetPasswordInputDTO;
import org.github.zuuuyao.service.user.dto.input.UserQueryPageInputDTO;
import org.github.zuuuyao.service.user.dto.output.UserVo;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @Desc
 * @Time 2024-07-11 16:32
 * @Author HuangZhongYao
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    IUserService userService;

    @Operation(summary = "分页查询", description = "分页查询用户接口")
    @GetMapping(value = "/pageQueryList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperationSupport(authors = "zuuuYao")
    public Page<UserVo> pageQueryList(UserQueryPageInputDTO inputDTO) {
        return userService.pageQueryList(inputDTO);
    }


    @Operation(summary = "添加用户", description = "添加用户接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "411", description = "账号已存在")
    @PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addUser(@RequestBody @Validated(Group.Insert.class) AddUserInputDTO inputDTO) {
        return userService.addUser(inputDTO);
    }

    @Operation(summary = "删除用户")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(value = "/delUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delUser(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return userService.delUser(inputDTO);
    }

    @Operation(summary = "重置密码")
    @ApiResponse(responseCode = "200", description = "OK")
    @PatchMapping(value = "/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean resetPassword(@RequestBody @Validated ResetPasswordInputDTO inputDTO) {
        return userService.resetPassword(inputDTO);
    }

}
