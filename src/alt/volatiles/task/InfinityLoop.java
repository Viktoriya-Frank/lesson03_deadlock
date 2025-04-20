package alt.volatiles.task;

import java.util.concurrent.atomic.AtomicBoolean;

public class InfinityLoop implements Runnable{
//    private volatile boolean flag = true;
private AtomicBoolean flag= new  AtomicBoolean(true);

    public boolean isFlag() {
        return flag.get();
    }

    public void setFlag(boolean flag) {
//        this.flag = flag;
        this.flag.set(flag);
    }

    @Override
    public void run() {
        while (isFlag()){
            // Do something
        }
        System.out.println(Thread.currentThread().getName() + " is finished");
    }
}
