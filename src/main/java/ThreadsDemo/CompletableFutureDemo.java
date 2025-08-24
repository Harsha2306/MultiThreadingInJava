package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Result form CompletableFuture";
        }).thenAccept(result -> {
            log.info("CompletableFuture result: {}", result);
            log.info("Processing after CompletableFuture result");
        });
        log.info("Main thread is free to do other tasks while waiting");
        Thread.sleep(2000);
    }
}
