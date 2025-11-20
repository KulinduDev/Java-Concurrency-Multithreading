import java.util.concurrent.Semaphore;

public class SharedBuffer {

    private final String[] buffer  = new String[3]; // small buffer of size 3
    private int count = 0; // how many items currently in buffer

    // Semaphores
    private Semaphore emptySlots = new Semaphore(3); // start with 3 empty slots
    private Semaphore filledSlots = new Semaphore(0); // starts with 0 filled slots
    private Semaphore mutex = new Semaphore(1); // for mutual exclusion. like a lock

    public void produce(String item, String producerName) throws InterruptedException {

        //wait for empty slot
        System.out.println(producerName + "waiting for empty slot...");
        emptySlots.acquire();

        //get exclusive access to buffer which is the shared data structure which temporarily stores items produced by one or more producer threads and items consumed by one or more threads
        mutex.acquire();

        // critical section only one thread at a time here
        buffer[count] = item;
        count++;
        System.out.println(producerName + " produced: " + item + " | buffer: " + count + "/3");

        //release exclusive access
        mutex.release();

        //signal that a new thread is available
        filledSlots.release();



    }

    public String consume(String consumerName) throws InterruptedException{
        // wait for a filled slot
        System.out.println(consumerName + "waiting for a item..");
        filledSlots.acquire();

        //get exclusive access to buffer
        mutex.acquire();

        //critical section - only one thread at a time here
        count--;
        String item = buffer[count];
        System.out.println(consumerName + "consumed: " + item + " | buffer: " + count + "/3");

        //release the exclusive access
        mutex.release();

        //signal that a new empty slot is available
        emptySlots.release();

        return item;
    }
}

