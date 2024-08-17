package org.github.zuuuyao.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.web.BaseController;
import org.github.zuuuyao.service.bustruck.dto.input.AddBusTruckInputDTO;
import org.github.zuuuyao.service.dict.IDictService;
import org.github.zuuuyao.service.dict.dto.inpnt.AddDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.EditDictInputDTO;
import org.github.zuuuyao.service.dict.dto.inpnt.SetStateDictInputDTO;
import org.github.zuuuyao.service.dict.output.DictDataVO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
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

    @Operation(summary = "获取字典数据", description = "根据字典类型id获取数据")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(value = "/queryList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DictDataVO> queryList(@RequestParam(name = "dictTypeId") Long dictTypeId) {
        return dictService.queryList(dictTypeId);
    }

    @Operation(summary = "添加字典数据", description = "添加字典数据接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping(value = "/addDict", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addDict(@RequestBody @Validated AddDictInputDTO inputDTO) {
        return dictService.addDict(inputDTO);
    }

    @Operation(summary = "删除字典数据", description = "删除字典数据接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(value = "/delDict", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delDict(@RequestBody @Validated BaseManyLongIdInputDTO inputDTO) {
        return dictService.delDict(inputDTO);
    }

    @Operation(summary = "编辑字典数据", description = "编辑字典接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping(value = "/editDict", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean editDict(@RequestBody @Validated EditDictInputDTO inputDTO) {
        return dictService.editDict(inputDTO);
    }

    @Operation(summary = "启用|禁用字典数据", description = "启用|禁用字典接口")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping(value = "/setStateDict", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean setStateDict(@RequestBody @Validated SetStateDictInputDTO inputDTO) {
        return dictService.setStateDict(inputDTO);
    }
}
