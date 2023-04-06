package com.xzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 说明：在Spring项目运行的过程中动态的修改定时任务的执行周期
 *
 * @author xzy
 * @date 2023/4/6  21:10
 */
@EnableScheduling
@SpringBootApplication
public class DynamicSchedulingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicSchedulingApplication.class, args);
    }
}
