import java.util.concurrent.*;

public class CallableAndFutureDemo {

    public static void main(String[] args){

        //create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //callable tasks - they return results

        //producer which returns what it produced

        Callable<String> producerTask = () ->{
            StringBuilder result = new StringBuilder();
            for(int i=1;i<=3;i++){
                String item = "item-" + i;
                result.append(item).append(" ");
                System.out.println("producing: " + item);
                Thread.sleep(1000);
            }

            // toString() method in java is used to return a string representation of an object

            return "producer result: " + result.toString();
        };

        // consumer that returns what is consumed
         Callable<String> consumerTask = () ->{
             StringBuilder result = new StringBuilder();
             for(int i=1;i<=3;i++){
                 String item = "item-" + i;
                 result.append(item).append(" ");
                 System.out.println("consuming: " + item);
                 Thread.sleep(1500);
             }
             return "consumer result " + result.toString();
         };

         // submit callables and get futures
        System.out.println("submitting callable tasks..");

        // submit() method returns a future object
        Future<String> producerFuture = executor.submit(producerTask);
        Future<String> consumerFuture = executor.submit(consumerTask);

        // get results from futures
        try{
            System.out.println("doing other work while tasks run...");
            Thread.sleep(500);

            // check if tasks are done (none-blocking)
            System.out.println("producer done? " + producerFuture.isDone());
            System.out.println("consumer done? " + consumerFuture.isDone());

            //get() method BLOCKS until result is available
            System.out.println("\nwaiting for results");
            String producerResult = producerFuture.get();
            String consumerResult = consumerFuture.get();

            System.out.println("\n=== RESULTS ===");
            System.out.println(producerResult);
            System.out.println(consumerResult);

        }catch(InterruptedException e){
            System.out.println("main thread interrupted");
        }catch(ExecutionException e){
            System.out.println("task execution failed " + e.getCause());
        }finally{
            executor.shutdown();
        }


    }

}
