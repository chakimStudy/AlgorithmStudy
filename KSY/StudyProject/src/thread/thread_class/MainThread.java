package thread.thread_class;

public class MainThread {
    public static void main(String[] args) {
        System.out.println("NEW : main thread");
        System.out.println("RUNNING 1 : " + SubThread.currentThread().getName());

        SubThread subThread = new SubThread();
//        subThread.start();

        System.out.println("RUNNING 3 : " + SubThread.currentThread().getName());
        System.out.println("TERMINATED : main thread");
    }
}
