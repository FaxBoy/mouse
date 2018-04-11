package cn.uicp.mouse.note.thread;

public class ThreadMain extends Thread{

	public void run() {
		for (int i = 0; i < 10000; i++) {  
            System.out.println("线程一"+Thread.currentThread().getName()+":"+i);  
        }  
	}
	
	public static void main(String[] args) {
		ThreadMain t1 = new ThreadMain();
		ThreadMain t2 = new ThreadMain();
		t1.start();
		t2.start();
	}
	
}
