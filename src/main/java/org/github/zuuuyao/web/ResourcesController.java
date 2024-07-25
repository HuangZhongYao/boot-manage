package org.github.zuuuyao.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.service.resources.IResourcesService;
import org.github.zuuuyao.service.resources.dto.input.AddResourcesInputDTO;
import org.github.zuuuyao.service.resources.dto.input.EditResourcesInputDTO;
import org.github.zuuuyao.service.resources.dto.input.SetResourcesStateInputDTO;
import org.github.zuuuyao.service.resources.model.ResourcesTreeVo;
import org.github.zuuuyao.service.resources.model.ResourcesVo;
import org.github.zuuuyao.service.role.dto.input.SetRoleStateInputDTO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "查询资源下按钮", description = "查询资源下的按钮")
    @GetMapping(value = "/button/{parentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperationSupport(authors = "zuuuYao")
    public List<ResourcesVo> button(@PathVariable(name = "parentId", required = true) Long parentId) {
        return resourcesService.button(parentId);
    }

    @GetMapping(value = "/resourcesTree", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "查询资源树", description = "查询资源树返回全部资源")
    @ApiResponse(responseCode = "200", description = "ok")
    public List<ResourcesTreeVo> resourcesTree() {
        return resourcesService.resourcesTree();
    }

    @Operation(summary = "添加资源", description = "添加资源接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping(value = "/addResources", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addResources(@RequestBody @Validated AddResourcesInputDTO inputDTO) {
        return resourcesService.addResources(inputDTO);
    }

    @Operation(summary = "编辑资源", description = "编辑资源接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PatchMapping(value = "/editResources", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean editResources(@RequestBody @Validated EditResourcesInputDTO inputDTO) {
        return resourcesService.editResources(inputDTO);
    }

    @Operation(summary = "启用|停用资源", description = "启用|停用资源接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PatchMapping(value = "/setState", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean setState(@RequestBody @Validated SetResourcesStateInputDTO inputDTO) {
        return resourcesService.setState(inputDTO);
    }

    @Operation(summary = "删除资源")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(value = "/delResources", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delResources(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return resourcesService.delResources(inputDTO);
    }

}
