package ps.ㄱSolving;

public class ThreadStudy {
    public static void main(String[] args) {
        System.out.println("Thread name : " + Thread.currentThread().getName());
        System.out.println("Thread Group : " + Thread.currentThread().getThreadGroup());
        System.out.println("Thread Id : " + Thread.currentThread().getId());
        System.out.println("Thread Priority : " + Thread.currentThread().getPriority());
        System.out.println("Thread State : " + Thread.currentThread().getState());
        System.out.println("Thread Context Class Loader : " + Thread.currentThread().getContextClassLoader());
    }
}
