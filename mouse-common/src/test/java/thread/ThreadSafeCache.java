package thread;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: ThreadSafeCache
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/6/18 15:43
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/6/18        shilun           v1.0.0               创建
 */
public class ThreadSafeCache {
    volatile int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static void main(String[] args) {
        ThreadSafeCache threadSafeCache = new ThreadSafeCache();

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                int x = 0;
                while (threadSafeCache.getResult() < 100) {
                    x++;
                }
                System.out.println(x);
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadSafeCache.setResult(200);
    }
}

