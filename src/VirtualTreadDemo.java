/**
 * Demonstrates the creation of virtual threads using Thread.ofVirtual()
 * to safely increment and decrement a shared counter.
 */
public class VirtualTreadDemo {

    public static void main(String[] args) throws InterruptedException {
        // 1. Create a new object of SharedCounter
        SharedCounter counter = new SharedCounter();

        // --- Step 4 & 6: Create the FIRST virtual thread (Unstarted, then manually started) ---
        // 2. Create the UNSTARTED virtual thread (increment)
        // The Runnable is provided as a lambda expression.
        Thread incrementThread = Thread.ofVirtual().unstarted(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
                System.out.println("Increment Thread : " + counter.getValue());
                try {
                    Thread.sleep(200); // Sleep for 200ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });

        // --- Step 5: Create the SECOND virtual thread (Started immediately) ---
        // 1. Create and START this virtual thread immediately (decrement)
        Thread decrementThread = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 5; i++) {
                counter.decrement();
                System.out.println("Decrement Thread : " + counter.getValue());
                try {
                    Thread.sleep(200); // Sleep for 200ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });

        // Step 6: Start the unstarted increment thread
        System.out.println("Starting the unstarted increment thread...");
        incrementThread.start();

        // Step 7: Wait for both threads to finish using join()
        System.out.println("\nWaiting for both virtual threads to complete...");
        incrementThread.join();
        decrementThread.join();

        // Step 8: Print the final value
        System.out.println("\nFinal Value = " + counter.getValue());
        // Expected result is 0 (5 increments and 5 decrements)
    }
}
