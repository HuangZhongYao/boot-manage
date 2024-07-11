package org.github.zuuuyao;

import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;

@SpringBootApplication
public class BootManageApplication implements ApplicationRunner {

    @Resource
    ServerProperties serverProperties;

    public static void main(String[] args) {
        SpringApplication.run(BootManageApplication.class, args);
        System.getProperties();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        ServerProperties.Undertow undertow = serverProperties.getUndertow();
        System.out.println("undertow = " + undertow);
    }
}
