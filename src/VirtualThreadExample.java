public class VirtualThreadExample {

    public static void main(String[] args){

        Runnable runnable = () -> {
            for (int i = 0;i<10;i++){
                System.out.println("Index: " + i);
            }
        };

        Thread vThread = Thread.ofVirtual().start(runnable);

        Thread vThreadUnstarted = Thread.ofVirtual().unstarted(runnable);

        vThreadUnstarted.start();

        try{
            vThreadUnstarted.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
