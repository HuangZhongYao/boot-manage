package org.github.zuuuyao.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.service.resources.IResourcesService;
import org.github.zuuuyao.service.resources.dto.input.AddResourcesInputDTO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源接口控制器
 *
 * @Desc
 * @Time 2024-07-11 16:32
 * @Author HuangZhongYao
 */
@Tag(name = "资源接口", description = "菜单资源模块接口")
@RestController
@RequestMapping("/resources")
@AllArgsConstructor
public class ResourcesController {

    IResourcesService resourcesService;

    @Operation(summary = "分页查询", description = "分页查询资源接口")
    @GetMapping(value = "/pageQueryList", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters({
        @Parameter(name = "current", description = "页码"),
        @Parameter(name = "size", description = "每页显示条数"),
    })
    @ApiOperationSupport(authors = "zuuuYao")
    public Page pageQueryList(BaseQueryPageInputDTO inputDTO) {
        return resourcesService.pageQueryList(inputDTO);
    }


    @Operation(summary = "添加资源", description = "添加资源接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "资源编码已存在")
    @PostMapping(value = "/addResources", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addResources(@RequestBody @Validated AddResourcesInputDTO inputDTO) {
        return resourcesService.addResources(inputDTO);
    }

    @Operation(summary = "删除资源")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping(value = "/delResources", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delResources(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return resourcesService.delResources(inputDTO);
    }

}
