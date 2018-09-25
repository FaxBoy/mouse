package test.zk;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

public class JoinGroup extends ConnectionWatcher {

	private static final String HOST1 = "192.168.56.90:2181";
	private static final String HOST2="10.37.129.3:2181,10.37.129.4:2181,10.37.129.5:2181";

	public void join(String groupName, String memberName) throws KeeperException, InterruptedException {
		String path = "/" + groupName + "/" + memberName;
		String createdPath = zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println("Created:" + createdPath);
	}

	public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
		JoinGroup joinGroup = new JoinGroup();
		joinGroup.connect(HOST2);
		joinGroup.join("a", "aa");
		Thread.sleep(Long.MAX_VALUE);
	}

}
