package org.github.zuuuyao.web;

import cn.dev33.satoken.annotation.SaIgnore;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.github.zuuuyao.common.base.web.BaseController;
import org.github.zuuuyao.service.generate.IGenerateService;
import org.github.zuuuyao.service.generate.dto.TableVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Desc
 * @Time 2024-07-29 10:02
 * @Author HuangZhongYao
 */
@SaIgnore
@Tag(name = "代码生成",description = "代码生成相关接口")
@RestController
@RequestMapping("/generate")
public class GenerateController extends BaseController {

    @Resource
    IGenerateService generateService;
    @GetMapping(value = "/getTables",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "获取全部表",description = "获取表用户代码生成")
    @ApiOperationSupport(authors = {"zuuuYao"})
    public List<TableVO> getTables(){
        return generateService.getTables();
    }
}
