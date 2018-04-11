package cn.uicp.mouse.note.thread;


public class ThreadMain2 implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10000; i++) {  
            System.out.println("线程一"+Thread.currentThread().getName()+":"+i);  
        }  
	}
	
	public static void main(String[] args) {
		ThreadMain2 t1 = new ThreadMain2();
		ThreadMain2 t2 = new ThreadMain2();
		Thread th1 = new Thread(t1);
		Thread th2 = new Thread(t2);
		th1.start();
		th2.start();
	}
	

}
