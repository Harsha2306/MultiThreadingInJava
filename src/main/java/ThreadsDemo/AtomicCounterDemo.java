package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicCounterDemo {
    private final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        AtomicCounterDemo atomicCounterDemo = new AtomicCounterDemo();
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            for (int i = 1; i <= 10; i++) {
                executor.submit(atomicCounterDemo::increment);
            }
        }
        Thread.sleep(2000);
        log.info("final count value {}", atomicCounterDemo.getCounter());
    }

    private int getCounter() {
        return counter.get();
    }

    private void increment() {
        log.info("Thread {} incremented counter to {}", Thread.currentThread().getName(), counter.incrementAndGet());
    }
}
