package Threading;

import java.util.concurrent.CountDownLatch;

public class Client {
    public static void main(String[] args) {
        final int THREAD = 8;
        SimpleCounter sm = new SimpleCounter(0);
        CountDownLatch latch = new CountDownLatch(8);
        for (int i = 0; i < THREAD; i++) {
            new Thread(() -> {
                sm.inc();
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.getMessage();
        }
        System.out.println("FINAL NUMBER: " + sm.getCount() + ", DONE");
    }
}
