package Threading.ThreadPool2;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class ThreadsClient {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 35; i++) {
            FileProcessor fp = new FileProcessor(latch);
            pool.execute(fp);
        }

        // a thread to monitor the pool
        new Thread(() -> {
            while (!pool.isTerminated()) {
                System.out.println("Active Thread: " + pool.getActiveCount());
                System.out.println("Task Completed: " + pool.getCompletedTaskCount());
                System.out.println("Total Task: " + pool.getTaskCount());
            }
        }).start();
        latch.countDown();
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted");
        }
        System.out.println("All tasks completed.");

    }
}
