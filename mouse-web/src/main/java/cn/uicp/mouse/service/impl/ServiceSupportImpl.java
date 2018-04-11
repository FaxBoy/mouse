package cn.uicp.mouse.service.impl;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.uicp.mouse.dao.SupportDao;
import cn.uicp.mouse.service.ServiceSupport;
import cn.uicp.mouse.util.mybatis.paginator.domain.Order;
import cn.uicp.mouse.util.mybatis.paginator.domain.PageBounds;


/**
 * 
* @ClassName: ServiceSupportImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author shil
* @date 2017年4月25日 下午1:40:53 
* 
* @param <T> 实体类
* @param <ID> 主键类型
 */
public class ServiceSupportImpl<T, ID extends Serializable> implements ServiceSupport<T,ID>{

	private static final Logger logger = Logger.getLogger(ServiceSupportImpl.class);

	@Autowired
	private SupportDao<T, ID> DaoSupport;
	
	public Integer insert(T entity) {
		return DaoSupport.insert(entity);
	}
    
	public Integer insert(Map params) {
		return DaoSupport.insert(params);

	}
	public T load(ID id) {
		return DaoSupport.load(id);
	}

	public List select(Map params) {
		return DaoSupport.select(params);
	}
 
	public List selectPage(Map params,int pageSize) {
		return DaoSupport.selectPage(params, new PageBounds(pageSize));
	}
	
	public List selectPage(Map params, int page, int pageSize) {
		return DaoSupport.selectPage(params, new PageBounds(page, pageSize));
	}
	
	public List selectPage(Map params, int pageSize, String sortString) {
		return DaoSupport.selectPage(params, new PageBounds(pageSize,Order.formString(sortString)));
	}
	
	public List selectPage(Map params, int page, int pageSize, String sortString) {
		return DaoSupport.selectPage(params, new PageBounds(page, pageSize,Order.formString(sortString)));
	}
	
	public Integer selectCountByMap(Map params) {
		return DaoSupport.selectCountByMap(params);
	}

	public Integer deleteByPrimaryKey(ID id) {
		return DaoSupport.deleteByPrimaryKey(id);
	}
	
	public Integer deleteByPrimaryKey(T entity) {
		return DaoSupport.deleteByPrimaryKey(entity);
	}

	public Integer insertSelective(T entity) {
		return DaoSupport.insertSelective(entity);
	}

	public T selectByPrimaryKey(ID id) {
		return DaoSupport.selectByPrimaryKey(id);
	}
	
	public T selectByPrimaryKey(T entity) {
		return DaoSupport.selectByPrimaryKey(entity);
	}

	public Integer updateByPrimaryKeySelective(T entity) {
		return DaoSupport.updateByPrimaryKeySelective(entity);
	}

	public Integer updateByPrimaryKey(T entity) {
		return DaoSupport.updateByPrimaryKey(entity);
	}
	
	public Integer updateList(Map params){
		return DaoSupport.updateList(params);
	}

	public Integer deleteByMap(Map params) {
		return DaoSupport.deleteByMap(params);
	}
	public Integer deleteList(List list){
		return DaoSupport.deleteList(list);
	}
}
