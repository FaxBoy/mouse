package thread;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName thread1
 * @Description TODO 通过实现Runnable接口 实现多线程
 * @date 2018/9/14 11:02
 */
public class Thread1 implements Runnable{
    @Override
    public void run() {

        for (int i = 0; i < 50 ;i++){
            System.out.println("运行："+i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(11);
//        Thread1 thread1 = new Thread1();
        Thread t = new Thread(new Thread1());
        t.start();
        t.join();
        new Thread(new Thread1()).start();
        new Thread(new Thread1()).start();
    }
}
