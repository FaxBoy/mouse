package cn.uicp.mouse.note.subtree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: TreeMain 
* @Description: TODO(二叉树) 
* @author shil
* @date 2018年3月29日 下午1:06:25 
*
 */
public class TreeMain {

	private Node root;

	public TreeMain() {
		init();
	}

	/**
	 * 初始化树
	* @Title: init 
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author shil
	* @date 2018年3月29日 上午11:34:32 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void init() {
		Node x = new Node("X", null, null);
		Node y = new Node("Y", null, null);
		Node d = new Node("d", x, y);
		Node e = new Node("e", null, null);
		Node f = new Node("f", null, null);
		Node c = new Node("c", e, f);
		Node b = new Node("b", d, null);
		Node a = new Node("a", b, c);
		root = a;
	}
	
	public static void main(String[] args) {
		TreeMain tree = new TreeMain();
		//System.out.println(tree.root);
		List<Node> list=new ArrayList<Node>();
		list=Node.postOrder(tree.root, list);
		for (Node node : list) {
			System.out.println(node.data);
		}
	}

}
