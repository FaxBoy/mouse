package cn.uicp.mouse.note.thread;

import cn.uicp.mouse.note.thread.Factory.Person;

/**
 * 线程同步
 * 
 * @ClassName: ThreadSynchronization
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shil
 * @date 2018年3月29日 下午4:36:05
 *
 */
public class ThreadSynchronization {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		Factory factory = new Factory();
		Person p1 = factory.getPerson();
		Person p2 = factory.getPerson();
		p1.start();
		p2.start();
		
		long endTime = System.currentTimeMillis();    //获取结束时间

		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
	}

}
