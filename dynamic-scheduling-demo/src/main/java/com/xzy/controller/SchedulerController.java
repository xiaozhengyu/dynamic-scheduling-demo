package com.xzy.controller;

import com.xzy.scheduler.SchedulerAaa;
import com.xzy.scheduler.SchedulerBbb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 说明：控制定时任务
 *
 * @author xzy
 * @date 2023/4/6  21:23
 */
@Slf4j
@RestController
@RequestMapping("scheduler")
public class SchedulerController {

    @Resource
    private SchedulerAaa schedulerAaa;

    @Resource
    private SchedulerBbb schedulerBbb;

    @GetMapping("/changeSchedulerCron/aaa")
    public String changeSchedulerAaaCron(@RequestParam String cron) {
        log.info("TaskA's cron is change to {}", cron);
        schedulerAaa.setCron(cron);
        return "OK";
    }

    @GetMapping("/changeSchedulerCron/bbb")
    public String changeSchedulerAaaCron(@RequestParam Long timer) {
        log.info("TaskB's timer is change to {}", timer);
        schedulerBbb.setTimer(timer);
        return "OK";
    }
}
