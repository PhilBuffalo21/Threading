package Threading.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;

public class PoolClient {
    public static void main(String[] args) {
        RequestHandler task = new RequestHandler();
        final int THREAD = 8;
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD);
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 25; i++) {
            pool.execute(() -> {
                try {
                    latch.await();
                    task.requesting();
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            });
        }
        new Thread(() -> {
            while (!pool.isTerminated()) {
                System.out.println("Active Threads: " + pool.getActiveCount());
                System.out.println("Completed Tasks: " + pool.getCompletedTaskCount());
                System.out.println("Total Tasks: " + pool.getTaskCount());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        latch.countDown();
        pool.shutdown();

        // Optional: Wait for termination of all tasks
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted");
        }
        System.out.println("All tasks completed.");
    }
}
