package org.github.zuuuyao;

import jakarta.annotation.Resource;
import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class BootManageApplication implements ApplicationRunner {


    @Resource
    Environment env;
    public static void main(String[] args) {
        SpringApplication.run(BootManageApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application Started Successfully ! ");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:{}\n\t" +
                "External: \thttp://{}:{}\n\t"+
                "Doc: \thttp://{}:{}/doc.html\n"+
                "----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"));
    }
}
