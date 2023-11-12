package Threading.ThreadPool2;

import java.util.concurrent.CountDownLatch;

public class FileProcessor implements Runnable {
    String message;
    ProcessingStatus ps;
    CountDownLatch latch;

    public FileProcessor(String m, CountDownLatch latch) {
        this.latch = latch;
        message = m;
        ps = new ProcessingStatus();
    }

    public FileProcessor(CountDownLatch latch) {
        this.latch = latch;
        message = RandomStringGenerator.generateRandomString(10);
        ps = new ProcessingStatus();
    }

    @Override
    public void run() {
        /* Processing the input word file */
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String processedMessage = message.toLowerCase(); // Convert to lowercase
        processedMessage = processedMessage.concat(" is smelly"); // Append additional text
        System.out.println(processedMessage);
        ps.inc();

    }

}
