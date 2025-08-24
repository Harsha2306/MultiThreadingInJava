package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledExecutorDemo {
    public static void main(String[] args) {
        try (ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(3)) {
            scheduledExecutor.schedule(() -> log.info("ran after 3 seconds"), 3, TimeUnit.SECONDS);
        }
    }
}
