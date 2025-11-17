public class ReorderingProblem {

    // by default, no happens-before relationships between these
    private int data = 0;
    private boolean ready = false;
    private final Object lock = new Object(); // the lock

    // threadA's method
    public void writer(){
        // these can be reordered
        data = 42; // write1
        ready = true; // write 2

    }

    //thread B's method
    public void reader(){
        if(ready){ // read 1
            // we might see ready=true , but data=0
            System.out.println("data: " + data); // read 2
        }
    }

    // to fix using synchronized
    public void synchronizedWriter(){
        synchronized (lock){
            data=42;
            ready=true;
        }// unlock happens before
    }

    public void synchronizedReader(){
        synchronized (lock){
            if(ready){
                // guaranteed to see data as 42
                System.out.println(" synchronized data: " + data); // read 2

            }
        }
    }

    public static void main(String[] args){
        // you cant easily prove the problem but you can trust that the synchronized solution is always correct

        ReorderingProblem rp = new ReorderingProblem();
        // this is the only safe way to call them from different threads
        new Thread(rp::synchronizedWriter).start();
        new Thread(rp::synchronizedReader).start();
    }
}

