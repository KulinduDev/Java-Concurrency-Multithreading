/**
 * Class to hold and safely modify a shared integer variable.
 * All modification and access methods are synchronized.
 */
class SharedCounter {
    // 1. Private shared integer variable
    private int value = 0;

    /**
     * Safely increments the shared value.
     */
    public synchronized void increment() {
        value++;
    }

    /**
     * Safely decrements the shared value.
     */
    public synchronized void decrement() {
        value--;
    }

    /**
     * Safely returns the current value.
     * @return The current value of the counter.
     */
    public synchronized int getValue() {
        return value;
    }
}
