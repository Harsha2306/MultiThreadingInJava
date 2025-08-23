package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CallableDemo {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        // try with resources, so that executorService will automatically shut down without finally block
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            MyCallable myCallable1 = new MyCallable();
            MyCallable myCallable2 = new MyCallable();

            Future<String> future1 = executor.submit(myCallable1);
            Future<String> future2 = executor.submit(myCallable2);
            Future<String> future3 = executor.submit(myCallable1);

            log.info("result from first task {}", future1.get()); // blocks until the task completes
            log.info("result from second task {}", future2.get()); // blocks until the task completes
            log.info("result from third task {}", future3.get()); // blocks until the task completes
        }
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() {
        return Thread.currentThread().getName();
    }
}