package com.xzy.scheduler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 说明：
 *
 * @author xzy
 * @date 2023/4/6  21:14
 */
@Data
@Slf4j
@Component
public class SchedulerAaa implements SchedulingConfigurer {

    @Value("${scheduler.test.cron:0/10 * * * * ?}")
    private String cron;

    /**
     * Callback allowing a {@link TaskScheduler
     * TaskScheduler} and specific {@link Task Task}
     * instances to be registered against the given the {@link ScheduledTaskRegistrar}.
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 动态的使用cron表达式设置执行周期
        taskRegistrar.addTriggerTask(() -> {
            // Do something
            log.info("TaskA is working on {}", LocalDateTime.now());
        }, triggerContext -> {
            // 使用CronTrigger触发器，可动态修改cron表达式来操作循环规则
            CronTrigger cronTrigger = new CronTrigger(cron);
            return cronTrigger.nextExecutionTime(triggerContext);
        });
    }
}
