package thread.runnable_interface;

public class MainThread {
    public static void main(String[] args) {
        System.out.println("NEW : main thread");
        System.out.println("RUNNING 1 : Main Thread");

        for (int i = 0; i < 10; i++){
            Thread thread = new Thread(new SubThread(i));
            thread.start();
        }

        System.out.println("RUNNING 3 : Main Thread");
        System.out.println("TERMINATED : main thread");
    }
}
