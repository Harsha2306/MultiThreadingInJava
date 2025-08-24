package ThreadsDemo;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Getter
public class SynchronizedMethodDemo {
    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedMethodDemo synchronizedMethodDemo = new SynchronizedMethodDemo();
        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            for (int i = 0; i < 5; i++)
                executor.submit(synchronizedMethodDemo::increment);
        }
        Thread.sleep(2000);
        log.info("final count value {}", synchronizedMethodDemo.getCount());
    }

    public synchronized void increment() {
        log.info("Synchronized Method - Start increment: {}", Thread.currentThread().getName());
        count++;
        log.info("Synchronized Method - Counter value after increment: {}", count);
        log.info("Synchronized Method - End increment: {}", Thread.currentThread().getName());
    }
}
