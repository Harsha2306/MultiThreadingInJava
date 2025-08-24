package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        try (ThreadPoolExecutor executor = new ThreadPoolExecutor(2, // corePoolSize
                4, // maximumPoolSize
                10, // keepAliveTime
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), // workQueue
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy() // rejection handler
        )) {
            for (int i = 1; i <= 6; i++) {
                final int taskId = i;
                executor.execute(() -> {
                    log.info("Task {} is running by {}", taskId, Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        log.error("Thread {} interrupted", Thread.currentThread().getName());
                    }
                });
            }
        }
    }
}
