import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreadsDemo {
    public static void main(String[] args){
        System.out.println("===TRADITIONAL THREADS===");
        testTraditionalThreads();

        System.out.println("===VIRTUAL THREADS===");
        testVirtualThreads();
    }


    static void testTraditionalThreads(){
        // limited by OS thread count - creating too many is expensive
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<String>> futures  = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        // try to create 100 tasks
        for(int i=0;i<=100;i++){
            final int taskId = i;
            Callable<String> task = () ->{
                String threadInfo = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " running on: " + threadInfo);

                //simulate I/O operation (waiting for database , file, network)
                Thread.sleep(100);

                return "Task-" + taskId + " completed";
            };
            futures.add(executor.submit(task));


        }

        // wait for all to complete
        for(Future<String> future : futures){
            try{
                future.get();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("traditional thread completed in: " + duration + "ms");
        executor.shutdown();
    }


    static void testVirtualThreads(){
        // virtual threads - cheap and lightweight!

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Future<String>> futures = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        // can create thousands of virtual threads easily
        for(int i=1;i<=100;i++){
            final int taskId = i;
            Callable<String> task = () ->{
                String threadInfo = Thread.currentThread().getName();
                System.out.println("task " + taskId + " running on; " + threadInfo);

                // simulate I/O operation virtual threads are perfect for this
                Thread.sleep(100);

                return "task-" + taskId + " completed";
            };
            futures.add(executor.submit(task));
        }

        //wait for all to complete
        for (Future<String> future : futures){
            try{
                future.get();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("virtual threads completed in: " + duration + "ms");
        executor.shutdown();
    }
}
