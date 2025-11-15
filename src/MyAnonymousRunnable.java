public class MyAnonymousRunnable {

    public static void main(String[] args){
        Runnable runnable = new Runnable(){
            @Override
                    public void run(){
                System.out.println("my anonymous runnable is running");
                System.out.println("my anonymous runnable is finished");

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
