package org.github.zuuuyao.web;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc
 * @Time 2024-07-08 15:21
 * @Author HuangZhongYao
 */
@RestController
public class Test {

    @Resource
    ServerProperties serverProperties;
    @GetMapping("/test")
    public Object test(){
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        return serverProperties;
    }
}
