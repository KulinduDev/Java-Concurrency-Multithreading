public class VisibilityProblem {

    private static volatile boolean   stop = false; // shared data

    public static void main(String[] args) throws InterruptedException{

        Thread workerThread = new Thread ( ()-> {
            System.out.println("worker thread starting..");
            while(!stop){

            }
            System.out.println("worker thread stopping");
        });

        workerThread.start();

        System.out.println("main thread sleeping");
        Thread.sleep(1000);

        System.out.println("main thread setting stop to true");
        stop=true;
    }
}
