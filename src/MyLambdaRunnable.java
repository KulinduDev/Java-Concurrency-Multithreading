public class MyLambdaRunnable {

    public static void main(String[] args){
        Runnable runnable = () ->{
            System.out.println("my lambda is running");
            System.out.println("my lambda is finished");

        } ;

        Thread thread1 = new Thread(runnable);
        thread1.start();
    }
}
