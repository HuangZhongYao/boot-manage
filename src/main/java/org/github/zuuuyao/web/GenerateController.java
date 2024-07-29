package org.github.zuuuyao.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.github.zuuuyao.common.base.web.BaseController;
import org.github.zuuuyao.service.generate.IGenerateService;
import org.github.zuuuyao.service.generate.dto.input.CodeGenerateInputDTO;
import org.github.zuuuyao.service.generate.dto.output.ColumnVO;
import org.github.zuuuyao.service.generate.dto.output.TableVO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Desc
 * @Time 2024-07-29 10:02
 * @Author HuangZhongYao
 */
@Tag(name = "代码生成", description = "代码生成相关接口")
@RestController
@RequestMapping("/generate")
public class GenerateController extends BaseController {

    @Resource
    IGenerateService generateService;

    @GetMapping(value = "/getTables", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "获取全部表", description = "获取表用于代码生成")
    @ApiOperationSupport(authors = {"zuuuYao"})
    public List<TableVO> getTables() {
        return generateService.getTables();
    }

    @GetMapping(value = "/getTableColumns", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "获取表字段信息", description = "获取表字段信息元数据信息")
    public List<ColumnVO> getTableColumns(@RequestParam(name = "tableName") String tableName) {
        return generateService.getTableColumns(tableName);
    }

    @PostMapping(value = "/codeGeneration", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Operation(summary = "代码生成", description = "代码生成接口,返回生成的代码zip压缩包二进制数据。需要保存或使用浏览器的下载功能")
    public void codeGeneration(@RequestBody @Validated CodeGenerateInputDTO inputDTO) {
        generateService.codeGeneration(inputDTO, super.getResponse());
    }
}
