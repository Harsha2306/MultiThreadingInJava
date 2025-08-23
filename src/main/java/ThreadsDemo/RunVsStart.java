package ThreadsDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunVsStart {
    public static void main(String[] args) {
        log.info(Thread.currentThread().getName()); //main
        Thread t1 = new Thread(new MyRunnable());
        t1.run(); // thread main is running
    }
}


