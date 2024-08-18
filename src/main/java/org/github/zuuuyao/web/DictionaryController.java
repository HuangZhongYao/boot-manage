package org.github.zuuuyao.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.web.BaseController;
import org.github.zuuuyao.service.dict.IDictService;
import org.github.zuuuyao.service.dict.dto.inpnt.AddDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.AddDictTypeInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.EditDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.EditDictTypeInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.SetStateDictInputDTO;
import org.github.zuuuyao.service.dict.output.DictDataVO;
import org.github.zuuuyao.service.dict.output.DictTypeTreeVO;
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
 * 字典模块控制器
 *
 * @Desc Created by IntelliJ IDEA.
 * @Author ZhongYao.Huang (https://github.com/HuangZhongYao)
 * @Copyright ZuuuuYao By Github
 * @Time 2024-08-18 4:06
 */
@Tag(name = "字典接口")
@AllArgsConstructor
@RestController
@RequestMapping("/dict")
public class DictionaryController extends BaseController {

    IDictService dictService;

    @Operation(summary = "获取字典类型Tree", description = "获取字典类型Tree结构接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(value = "/dictTypeTree", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DictTypeTreeVO> dictTypeTree() {
        return dictService.dictTypeTree();
    }

    @Operation(summary = "添加字典类型", description = "添加字典类型接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "450", description = "该字典类型已存在")
    @PostMapping(value = "/addDictType", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addDictType(@RequestBody @Validated AddDictTypeInputDTO inputDTO) {
        return dictService.addDictType(inputDTO);
    }

    @Operation(summary = "删除字典类型", description = "根据id删除接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(value = "/delDictType", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delDictType(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return dictService.delDictType(inputDTO);
    }

    @Operation(summary = "编辑字典类型", description = "编辑字典类型接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "450", description = "该字典类型已存在")
    @PatchMapping(value = "/editDictType", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addDictType(@RequestBody @Validated EditDictTypeInputDTO inputDTO) {
        return dictService.editDictType(inputDTO);
    }

    @Operation(summary = "启用|禁用字典类型", description = "启用|禁用字典类型接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PatchMapping(value = "/setStateDictType", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean setStateDictType(@RequestBody @Validated SetStateDictInputDTO inputDTO) {
        return dictService.setStateDictType(inputDTO);
    }

    @Operation(summary = "获取字典数据", description = "根据字典类型id获取数据")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(value = "/dictDataQueryList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DictDataVO> dictDataQueryList(@RequestParam(name = "dictTypeId") Long dictTypeId) {
        return dictService.dictDataQueryList(dictTypeId);
    }

    @Operation(summary = "添加字典数据", description = "添加字典数据接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "450", description = "该字典编码已存在")
    @ApiResponse(responseCode = "451", description = "该字典名称已存在")
    @PostMapping(value = "/addDictData", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addDictData(@RequestBody @Validated AddDictInputDTO inputDTO) {
        return dictService.addDictData(inputDTO);
    }

    @Operation(summary = "删除字典数据", description = "删除字典数据接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(value = "/delDictData", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delDictData(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return dictService.delDictData(inputDTO);
    }

    @Operation(summary = "编辑字典数据", description = "编辑字典接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "450", description = "该字典编码已存在")
    @ApiResponse(responseCode = "451", description = "该字典名称已存在")
    @PatchMapping(value = "/editDictData", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean editDictData(@RequestBody @Validated EditDictInputDTO inputDTO) {
        return dictService.editDictData(inputDTO);
    }

    @Operation(summary = "启用|禁用字典数据", description = "启用|禁用字典数据接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PatchMapping(value = "/setStateDictData", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean setStateDictData(@RequestBody @Validated SetStateDictInputDTO inputDTO) {
        return dictService.setStateDictData(inputDTO);
    }
}
