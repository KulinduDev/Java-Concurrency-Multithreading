import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args){
        SharedBuffer buffer = new SharedBuffer();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        //create producers
        for(int i=1;i<=2;i++){
            final int producerId = i;
            executor.execute(() ->{
                try{
                    for(int j = 1;j<=3;j++){
                        buffer.produce("P" + producerId + "-Item" + j, "producer-" + producerId);
                        Thread.sleep(1000);
                    }
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });
        }

        //create consumers
        for(int i=1;i<=2;i++){
            final int consumerId = i;
            executor.execute(() -> {
                try{
                    for(int j=1;j<=3;j++){
                        buffer.consume("Consumer-" + consumerId);
                        Thread.sleep(1500);
                    }
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        try{
            executor.awaitTermination(20, TimeUnit.SECONDS);
            System.out.println("\n All producer-consumer tasks completed!");
        }catch (InterruptedException e){
            executor.shutdownNow();
        }
    }
}
