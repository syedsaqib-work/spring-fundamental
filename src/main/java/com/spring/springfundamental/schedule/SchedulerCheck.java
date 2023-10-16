package com.spring.springfundamental.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class SchedulerCheck {

    //@Scheduled(fixedDelay = 5000)
    @Scheduled(cron = "0 31 23 ? * *")
    public void schedulePrint() {
        System.out.println("Hi I am Saqib -> " + LocalDateTime.now());
    }


/*    void startScheduler() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SchedulerTask(), 5000L);
    }*/
}

