package cn.uicp.mouse.note.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadTest {

    /**
     * 多线程处理list
     * 
     * @param data
     *            数据LinkedList,线程安全
     * @param threadNum
     *            线程数
     * @throws InterruptedException
     */
    public synchronized void handleList(LinkedList<String> data, int threadNum) throws InterruptedException {
        int length = data.size();
        int tl = length % threadNum == 0 ? length / threadNum : (length / threadNum + 1);
        CountDownLatch latch = new CountDownLatch(100);// 多少协作
        long a = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            int end = (i + 1) * tl;
            if ((i * tl) <= length) {
                // 继承thread启动线程
                // HandleThread thread = new HandleThread("线程[" + (i + 1) +"] ",data, i * tl, end > length ? length : end, latch);
                // thread.start();

                // 实现Runnable启动线程
                RunnableThread thread = new RunnableThread("线程[" + (i + 1) + "] ", data, i * tl, end > length ? length : end, latch);
                Thread runable = new Thread(thread);
                runable.start();
            }
        }
        latch.await();// 等待所有工人完成工作
        System.out.println("结束*****************************");
        long b = System.currentTimeMillis();
        System.out.println("时间:" + (b - a) + "毫秒***********************");
    }

    // 继承Thread
    class HandleThread extends Thread {
        private String threadName;
        private List<String> data;
        private int start;
        private int end;
        private CountDownLatch latch;

        public HandleThread(String threadName, List<String> data, int start, int end, CountDownLatch latch) {
            this.threadName = threadName;
            this.data = data;
            this.start = start;
            this.end = end;
            this.latch = latch;
        }

        public void run() {
            // TODO 这里处理数据
            List<String> l = data.subList(start, end);
            System.out.println(threadName + "--" + data.size() + "--" + start + "--" + end + "--");
            for (int i = 0; i < l.size(); i++) {
                // 单个线程中的数据
                System.out.println(l.get(i));
            }
            latch.countDown();// 工人完成工作，计数器减一
        }
    }

    // 实现Runnable
    class RunnableThread implements Runnable {
        private String threadName;
        private List<String> data;
        private int start;
        private int end;
        private CountDownLatch latch;

        public RunnableThread(String threadName, List<String> data, int start, int end, CountDownLatch latch) {
            this.threadName = threadName;
            this.data = data;
            this.start = start;
            this.end = end;
            this.latch = latch;
        }

        public void run() {
            // TODO 这里处理数据
            List<String> l = data.subList(start, end);
            System.out.println(threadName + "--" + data.size() + "--" + start + "--" + end + "--");
            for (int i = 0; i < l.size(); i++) {
                // 单个线程中的数据
                System.out.println(l.get(i));
            }
            latch.countDown();// 工人完成工作，计数器减一
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest test = new ThreadTest();

        // 准备数据
        LinkedList<String> data = new LinkedList<String>();
        for (int i = 0; i < 100000; i++) {
            data.add("item" + "  " + i);
        }
        test.handleList(data, 100);
        // System.out.println(ArrayUtils.toString(data));

    }
}