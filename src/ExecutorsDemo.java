import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {

    public static void main(String[] args) {

        // create a thread pool. this below line creates a pool with 2 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(2);


        // creates tasks
        // task 1 - producer

        Runnable producerTask = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 1; i <= 3; i++) {
                System.out.println(threadName + " producing item: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("producing task interrupted");
                }
            }
        };

        //task 2 - consumer

        Runnable consumerTask = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i <= 3; i++) {
                System.out.println(threadName + " consuming item: " + i);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.println("Consuming task interrupted ");
                }
            }
        };

        // submit tasks to executor
        System.out.println("submitting tasks to executor");
        executor.execute(producerTask);
        executor.execute(consumerTask);

        // important step - shutting down the executor because if not threads stay alive forever. however , no more tasks will be accepted after this


        System.out.println("no more tasks tasks will be accepted after this");
        executor.shutdown();

        try {
            // wait for running tasks to finish(maximum 10 seconds)
            if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("all tasks are completed successfully");
            } else {
                System.out.println("timeout! forcing shutdown");
                executor.shutdown(); // force stop if shutdown
            }
        }catch (InterruptedException e){
            executor.shutdown();
        }


    }
}
