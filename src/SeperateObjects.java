public class SeperateObjects {

    public static void main(String[] args){

        int myLocalVar = 0;
        String myLocalString = "text";

        // above are local variables because they are defined inside of a methods. so they can be only accessed within this method

        Runnable runnable1 = new MyRunnable();
        Runnable runnable2 = new MyRunnable();

        Thread thread1 = new Thread("Thread-1");
        Thread thread2 = new Thread("Thread-2");

        thread1.start();
        thread2.start();

    }
}
