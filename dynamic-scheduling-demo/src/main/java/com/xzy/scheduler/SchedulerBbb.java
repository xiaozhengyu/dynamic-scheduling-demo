package com.xzy.scheduler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.PeriodicTrigger;
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
public class SchedulerBbb implements SchedulingConfigurer {

    @Value("${scheduler.test.timer:1000}")
    private Long timer = 10000L;

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
            log.info("TaskB is working on {}", LocalDateTime.now());
        }, triggerContext -> {
            // 使用不同的触发器，为设置循环时间的关键，区别于CronTrigger触发器，该触发器可随意设置循环间隔时间，单位为毫秒
            PeriodicTrigger periodicTrigger = new PeriodicTrigger(timer);
            return periodicTrigger.nextExecutionTime(triggerContext);
        });
    }
}
