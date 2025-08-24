package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            Future<Integer> future = executor.submit(() -> {
                Thread.sleep(3000);
                return 30;
            });
            checkTaskStatus(future);
            log.info("Result:{}", future.get());
            checkTaskStatus(future);
        }
    }

    public static void checkTaskStatus(Future<Integer> future) {
        if (future.isDone())
            log.info("Task completed");
        else
            log.info("Still processing");
    }
}
