package alt.volatiles;

import alt.volatiles.task.InfinityLoop;

public class InfinityLoopAppl {
    public static void main(String[] args) throws InterruptedException {
        InfinityLoop loop = new InfinityLoop();
        Thread thread =  new Thread(loop);
        thread.start();
        Thread.sleep(3000);
        loop.setFlag(false);
        System.out.println("flag = " + loop.isFlag());
        System.out.println(Thread.currentThread().getName() + " is finished");

    }
}
