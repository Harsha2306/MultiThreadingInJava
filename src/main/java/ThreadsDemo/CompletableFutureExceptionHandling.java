package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class CompletableFutureExceptionHandling {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5)
                throw new RuntimeException("Something went wrong");
            return "success";
        }).exceptionally(ex -> {
            log.info("Exception caught: {}" + ex.getMessage());
            return "Recovery value";
        });
        log.info("Result: {}", future.join());
    }
}
