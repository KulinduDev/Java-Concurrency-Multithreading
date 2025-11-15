public class ThreadSleep {

    public static void main(String[] args){

        Runnable runnable = () -> {

            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " running");

            try{
                Thread.sleep(1000);// time to be inactive before its running
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(threadName + " finished");

        };

        Thread thread1 = new Thread(runnable,"The Thread");
        thread1.start();

    }
}
