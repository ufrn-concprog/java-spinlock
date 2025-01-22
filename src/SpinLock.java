import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLock {
    private final AtomicBoolean locked = new AtomicBoolean(false);

    public void lock() {
        while (!locked.compareAndSet(false, true)) {
            // blocks the thread until the lock is released
        }
    }

    public void unlock() {
        locked.set(false);
    }
}
