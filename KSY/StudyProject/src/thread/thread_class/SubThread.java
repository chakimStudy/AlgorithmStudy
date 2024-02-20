package thread.thread_class;

public class SubThread extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("NEW : sub thread");
        System.out.println("RUNNING 2 : " + SubThread.currentThread().getName());
        System.out.println("TERMINATED : sub thread");
    }
}
