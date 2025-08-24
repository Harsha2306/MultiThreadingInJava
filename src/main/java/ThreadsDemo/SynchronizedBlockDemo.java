package ThreadsDemo;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Getter
public class SynchronizedBlockDemo {
    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedBlockDemo synchronizedBlockDemo = new SynchronizedBlockDemo();
        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            for (int i = 0; i < 5; i++)
                executor.submit(synchronizedBlockDemo::increment);
        }
        Thread.sleep(2000);
        log.info("final count value {}", synchronizedBlockDemo.getCount());
    }

    public void increment() {
        log.info("Non Synchronized part {}", Thread.currentThread().getName());
        synchronized (this) {
            count++;
            log.info("Synchronized part - {} Counter value after increment: {}", Thread.currentThread().getName(), count);
        }
        log.info("Non Synchronized part {}", Thread.currentThread().getName());
    }
}
