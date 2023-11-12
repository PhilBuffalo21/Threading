package Threading.ThreadPool2;

public class FileProcessor implements Runnable {
    String message;

    public FileProcessor(String m) {
        message = m;
    }

    @Override
    public void run() {
        /* Processing the input word file */
        this.allLower();
        this.normalised();
    }

    private String allLower() {
        return message.toLowerCase();
    }

    private String normalised() {
        return message.concat("is smelly");
    }

}
