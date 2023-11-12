package Threading.ThreadPool2;

import java.util.concurrent.atomic.AtomicInteger;

public class ProcessingStatus {
    AtomicInteger counter;

    public ProcessingStatus() {
        counter = new AtomicInteger();
    }

    public void inc() {
        counter.incrementAndGet();
    }
}
