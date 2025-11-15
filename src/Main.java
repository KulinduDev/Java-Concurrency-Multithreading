public class Main {
    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        t1.start();

        MyRunnable task1 = new MyRunnable();
        Thread t2 = new Thread(task1);
        t2.start();
    }
}