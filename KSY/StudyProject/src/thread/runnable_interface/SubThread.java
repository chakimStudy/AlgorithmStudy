package thread.runnable_interface;

public class SubThread implements Runnable{
    int num;
    public SubThread(){
        this.num = 0;
    }
    public SubThread(int num){
        this.num = num;
    }
    @Override
    public void run() {
        System.out.println("NEW : sub thread " + this.num);
        try{
            System.out.println("RUNNING : SubThread " + this.num);
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("TERMINATED : sub thread " + this.num);
    }
}
