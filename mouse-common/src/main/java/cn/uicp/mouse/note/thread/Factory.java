package cn.uicp.mouse.note.thread;


public class Factory {
	int zhuantou = 20;

	public int getzhuantou() {
		synchronized(this) {//synchronized锁最小的范围，效率最快
			if (zhuantou == 0) {
				throw new RuntimeException(Thread.currentThread().getName() + ",没有砖头搬了!");
			}
			Thread.yield();
			return zhuantou--;
		}
		
	}

	// 工人
	class Person extends Thread {
		// 不停的搬砖
		public void run() {
			while (true) {
				// 获取线程名（工人名） 及 剩下砖头数
				System.out.println(getName() + "搬了第" + getzhuantou() + "块砖头");
				// 当线程的run方法中出现了异常，且我们没有 解决，那么该线程终止并死亡。但不会影响 当前进程中的其他线程。
				Thread.yield();
			}
		}
	}

	// 获取工人
	public Person getPerson() {
		return new Person();
	}

}
