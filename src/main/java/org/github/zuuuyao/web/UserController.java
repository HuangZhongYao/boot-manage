package org.github.zuuuyao.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.repository.UserRepository;
import org.github.zuuuyao.service.user.IUserService;
import org.github.zuuuyao.service.user.dto.input.AddUserInputDTO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    UserRepository userRepository;
    IUserService userService;

    @Operation(summary = "分页查询", description = "分页查询用户接口")
    @GetMapping(value = "/pageQueryList", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters({
        @Parameter(name = "current", description = "页码"),
        @Parameter(name = "size", description = "每页显示条数"),
    })
    @ApiOperationSupport(authors = "zuuuYao")
    public Page pageQueryList(BaseQueryPageInputDTO inputDTO) {
        return userService.pageQueryList(inputDTO);
    }


    @Operation(summary = "添加用户", description = "添加用户接口")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "401", description = "账号已存在")
    })
    @PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addUser(@RequestBody @Validated AddUserInputDTO inputDTO) {
        return userService.addUser(inputDTO);
    }

    @Operation(summary = "删除用户")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping(value = "/delUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delUser(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return userService.delUser(inputDTO);
    }

}
