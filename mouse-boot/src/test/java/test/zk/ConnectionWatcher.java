package test.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ConnectionWatcher implements Watcher{
	
	private static final int SESSION_TIMEOUT=5000;

	protected ZooKeeper zk;
    CountDownLatch connectedSignal=new CountDownLatch(1);
    
    public void connect(String host) throws IOException, InterruptedException{
        zk=new ZooKeeper(host, SESSION_TIMEOUT, this);
        connectedSignal.await();
    }

    public void process(WatchedEvent event) {
        if(event.getState()==KeeperState.SyncConnected){
            connectedSignal.countDown();
        }
    }
    public void close() throws InterruptedException{
        zk.close();
    }
    
    public static void main(String[] args) {
		int minVersion = 10;
		int maxVersion = 20;
		int nowVersion = 13;
		if(nowVersion<minVersion) {
			System.out.println("必须更新");
		}else if(nowVersion>=minVersion&&nowVersion<maxVersion) {
			System.out.println("提示更新，但非强制");
		}else {
			System.out.println("无需更新");
		}
	}

}
