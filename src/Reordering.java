public class Reordering {

    // by default no happens before relationship

    private int data = 0;
    private boolean ready = false;
    private final Object lock = new Object();

    public void writer(){
        data  =42;//write1
        ready = true;//read1
    }
    public void reader(){
        if(ready){//read1
            // might see ready=true , but data=0
            System.out.println("data: " + data);
        }
    }

    public void synchronizedWriter(){
        synchronized (lock){
            data=42;
            ready = true;
        }// unlock happens before
    }

    public void synchronizedReader(){
        synchronized (lock){
            if(ready){
                System.out.println("synchronized data: " + data);
                // guaranteed to see data=42
            }
        }
    }

    public static void main(String[] args){
        ReorderingProblem rp =new ReorderingProblem();
        new Thread(rp:: synchronizedWriter).start();
        new Thread(rp:: synchronizedReader).start();
    }
}
