package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

public class ThreadCreation {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t1.start();
        t2.start();
    }
}

@Slf4j
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            log.info("Thread {} is running: {}", Thread.currentThread().getName(), i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error("Thread interrupted");
            }
        }
    }
}

@Slf4j
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            log.info("Thread {} is running: {}", Thread.currentThread().getName(), i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error("Thread interrupted");
            }
        }
    }
}