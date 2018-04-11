package cn.uicp.mouse.util.mybatis.paginator.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 分页信息 如总页数 当前页数 总条数
* @ClassName: PageList 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author shil sl166199@163.com 
* @date 2016年8月3日 上午10:31:08 
* 
* @param <E>
 */
public class PageList<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1412759446332294208L;
    
    private Paginator paginator;

    public PageList() {}
    
	public PageList(Collection<? extends E> c) {
		super(c);
	}

	
	public PageList(Collection<? extends E> c,Paginator p) {
        super(c);
        this.paginator = p;
    }

    public PageList(Paginator p) {
        this.paginator = p;
    }


	/**
	 * 得到分页器，通过Paginator可以得到总页数等值
	 * @return
	 */
	public Paginator getPaginator() {
		return paginator;
	}

	
}
