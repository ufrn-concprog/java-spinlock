import java.util.ArrayList;

public class Main {
    private static final int NUM_THREADS = 5;

    public static void main(String[] args) {
        SpinLock slock = new SpinLock();

        Runnable criticalSection = () -> {
            slock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " is running its critical section");
            } finally {
                slock.unlock();
            }
        };

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(criticalSection, "Thread #" + (i+1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}