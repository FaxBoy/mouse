package test.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class CreateGroup implements Watcher{

	private static final int SESSION_TIMEOUT=5000;
    private static final String HOST1="192.168.56.90:2181";
    private static final String HOST2="10.37.129.3:2181,10.37.129.4:2181,10.37.129.5:2181";
    
    private ZooKeeper zk;
    private CountDownLatch connectedSignal=new CountDownLatch(1);
	
	@Override
	public void process(WatchedEvent event) {
		if(event.getState()==KeeperState.SyncConnected){
            connectedSignal.countDown();
        }
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect(HOST2);
        createGroup.create("c");
        createGroup.close();
    }

    private void close() throws InterruptedException {
        zk.close();
    }

    private void create(String groupName) throws KeeperException, InterruptedException {
        String path="/"+groupName;
        if(zk.exists(path, false)== null){
            zk.create(path, null/*data*/, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("Created:"+path);
    } 

    private void connect(String hosts) throws IOException, InterruptedException {
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        connectedSignal.await();
    }

}
