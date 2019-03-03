package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName ThreadPool1
 * @Description TODO 线程池
 * @date 2018/9/14 11:02
 */
public class ThreadPool1 {

    public static void main(String[] args) {

        //keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，
        //corePoolSiz: 设置核心池大小
        //maximumPoolSize: 设置线程池最大能创建的线程数目大小
        //workQueue: 一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响，一般来说，这里的阻塞队列有以下几种选择
        //unit: 参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        for(int i=0 ;i<15;i++){
            MyThread1 myTask = new MyThread1(i);
            threadPoolExecutor.execute(myTask);
            System.out.println("线程池中线程数目："+threadPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
                    threadPoolExecutor.getQueue().size()+"，已执行玩别的任务数目："+threadPoolExecutor.getCompletedTaskCount());
        }
        threadPoolExecutor.shutdown();
    }
}

class MyThread1 implements Runnable{

    private int num;

    public MyThread1(int num){
        this.num=num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task "+num);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+num+"执行完毕");
    }
}
