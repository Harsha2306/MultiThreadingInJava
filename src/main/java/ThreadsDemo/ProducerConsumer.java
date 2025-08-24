package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class ProducerConsumer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread producer = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() == CAPACITY) {
                    log.info("Buffer is full. Producer is waiting");
                    wait();
                }
                log.info("Producer produced: {}", value);
                buffer.offer(value++);
                notifyAll();
            }
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    log.info("Buffer is empty. Consumer is waiting");
                    wait();
                }
                int value = buffer.poll();
                log.info("Consumer consumed: {}", value);
                notifyAll();
            }
            Thread.sleep(1500);
        }

    }
}
