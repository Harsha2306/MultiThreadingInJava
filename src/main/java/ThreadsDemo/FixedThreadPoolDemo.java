package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 5; i++)
                executor.submit(new ThreadT(i));
        }
    }
}

@Slf4j
class ThreadT implements Runnable {
    private final int taskId;

    public ThreadT(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        log.info("{} is processing task: {}", Thread.currentThread().getName(), taskId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("Task interrupted: {}", e.getMessage());
        }
        log.info("{} finished task: {}", Thread.currentThread().getName(), taskId);
    }
}