package Threading.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
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
        latch.countDown();
        pool.shutdown();
    }
}
