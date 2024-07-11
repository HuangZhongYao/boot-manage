package org.github.zuuuyao.web;

import org.github.zuuuyao.common.base.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc
 * @Time 2024-07-11 16:16
 * @Author HuangZhongYao
 */
@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @GetMapping("/dashboard")
    public Object te(){
        return "";
    }
}
