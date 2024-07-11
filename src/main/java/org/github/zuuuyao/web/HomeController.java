package org.github.zuuuyao.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.github.zuuuyao.common.base.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc 主页控制器
 * @Time 2024-07-11 16:16
 * @Author HuangZhongYao
 */
@Tag(name = "主页接口",description = "主页接口")
@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @GetMapping("/dashboard")
    @Operation(summary = "主页统计接口")
    @ApiOperationSupport(authors = {"zuuuYao"})
    public Object te(){
        return "";
    }
}
