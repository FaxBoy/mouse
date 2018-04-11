package cn.uicp.mouse.note.subtree;

import java.util.List;

public class Node {

	public String data;

	/**
	 * 子树左
	 */
	private Node lchid;

	/**
	 * 子树右
	 */
	private Node rchid;

	public Node(String data, Node l, Node r) {
		this.data = data;
		this.lchid = l;
		this.rchid = r;
	}

	/**
	 * 前序遍历 根-左-右
	* @Title: preOrder 
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author shil
	* @date 2018年3月29日 下午12:38:37 
	* @param @param node
	* @param @param list    返回
	* @return void    返回类型 
	* @throws
	 */
	public static List<Node> preOrder(Node node,List<Node> list) {
		list.add(node); //先将根节点存入list
        //如果左子树不为空继续往左找，在递归调用方法的时候一直会将子树的根存入list，这就做到了先遍历根节点
        if(node.lchid != null)
        {
        		preOrder(node.lchid,list);
        }
        //无论走到哪一层，只要当前节点左子树为空，那么就可以在右子树上遍历，保证了根左右的遍历顺序
        if(node.rchid != null)
        {
        		preOrder(node.rchid,list);
        }
        return list;
	}
	
	/**
	 * 中序遍历 左-根-右
	* @Title: inOrder 
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author shil
	* @date 2018年3月29日 下午12:51:10 
	* @param @param node
	* @param @param list
	* @param @return    设定文件 
	* @return List<Node>    返回类型 
	* @throws
	 */
	public static List<Node> inOrder(Node node,List<Node> list) {
        //如果左子树不为空继续往左找
        if(node.lchid != null)
        {
        		inOrder(node.lchid,list);
        }
        	list.add(node); //左子树找完后进行出栈
        //右子树上遍历，保证了根左右的遍历顺序
        if(node.rchid != null)
        {
        		inOrder(node.rchid,list);
        }
        return list;
	}
	
	/**
	 * 后序遍历 左-右-根
	* @Title: postOrder 
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author shil
	* @date 2018年3月29日 下午12:59:31 
	* @param @param node
	* @param @param list
	* @param @return    设定文件 
	* @return List<Node>    返回类型 
	* @throws
	 */
	public static List<Node> postOrder(Node node,List<Node> list) {
        //先遍历左
        if(node.lchid != null)
        {
        		postOrder(node.lchid,list);
        }
        //右子树上遍历，保证了根左右的遍历顺序
        if(node.rchid != null)
        {
        		postOrder(node.rchid,list);
        }
        list.add(node);
        return list;
	}
	
}
