public class Visibility {

    private static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException{

        Thread wThread = new Thread(() ->{
            System.out.println("worker thread starting...");
            while(!stop){
                // this loop will run forever because it might never see the change to stop. its reading from its own cache copy of the stop

            }
            System.out.println("worker thread stopping");
        });
        wThread.start();

        System.out.println("main thread sleeping");
        Thread.sleep(1000);

        System.out.println("main thread setting stop to true");
        stop = true;
    }
}
