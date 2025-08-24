package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ShutDownVsShutDownNow {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                log.info("Task {} started", taskId);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log.error("Thread {} interrupted", Thread.currentThread().getName());
                }
                log.info("Task {} finished", taskId);
            });
        }
//        executor.shutdown();
//        log.info("executor shutdown initiated");
        executor.shutdownNow();
        log.info("executor shutdownNow initiated");
    }
}
