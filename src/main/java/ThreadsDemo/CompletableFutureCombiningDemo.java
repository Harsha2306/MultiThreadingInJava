package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class CompletableFutureCombiningDemo {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "Future 1";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "Future 2";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> combined = future1.thenCombine(future2, (result1, result2) -> result1 + " " + result2);
        log.info("Combined result {}", combined.join());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);
        allOf.thenRun(() -> log.info("Both futures completed!"));

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2);
        log.info("First completed: {}", anyOf.join());
    }
}
