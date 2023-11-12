package Threading;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCounter {
    private AtomicInteger count;

    public SimpleCounter(int i) {
        count = new AtomicInteger(i);
    }

    public SimpleCounter() {
        count = new AtomicInteger();
    }

    public int getCount() {
        return count.get();
    }

    public void inc() {
        count.incrementAndGet();
    }
}
