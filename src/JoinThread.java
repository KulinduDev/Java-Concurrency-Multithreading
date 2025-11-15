public class JoinThread {

    public static void main(String[] args){

        Runnable runnable = () ->{
            for(int i =0;i<5;i++){
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("running");
            }
        };

        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();

        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
