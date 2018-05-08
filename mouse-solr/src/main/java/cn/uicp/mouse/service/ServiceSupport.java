package cn.uicp.mouse.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 处理单表操作的所有操作
 * 目前只调用dao接口，操作数据库；暂时没有特别的作用，以后可以设置权限或者日志
 */
public interface ServiceSupport<T, ID extends Serializable>  {
	
	/**
	 * @description 通过主键查询model 
	 */
	public T load(ID id);
	
	/**
	 * @description 通过多条件查询集合 
	 */
	public List select(Map params);
	
	/**
	 * 
	* @Title: selectPage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param params 条件
	* @param @param page 页码
	* @param @param pageSize 分页数 
	* @param @return    设定文件 
	* @return List    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:43:49 
	* @throws
	 */
	public List selectPage(Map params,int page,int pageSize);
	
	/**
	 * @description 分页查询 带排序
	 */
	public List selectPage(Map params, int page,int pageSize, String sortString);
	
	/**
	 * @description 分页查询 带排序 是否count
	 */
	public List selectPage(Map params,int pageSize);
	
	/**
	 * @description 多条件查询条数
	 */
	public Integer selectCountByMap(Map params);
	
	/**
	 * @description 通过主键删除 
	 */
	public Integer deleteByPrimaryKey(ID id);
	
	/**
	 * @description 多条件删除
	 */
	public Integer deleteByMap(Map params);
	
	/**
	 * @Description 批量删除
	 */
	public Integer deleteList(List list);
	
	/**
	 * @description 通过实体对象插入 
	 */
	public Integer insert(T entity);
	
	/**
	 * @description 通过Map对象插入  
	 */
	public Integer insert(Map params);
	
	/**
	 * @description 多条件插入 
	 */
	public Integer insertSelective(T entity);
	
	/**
	 * @description 通过主键查询实体对象
	 */
	public T selectByPrimaryKey(ID id);
	
	public T selectByPrimaryKey(T entiry);
	
	/**
	 * @description 多条件修改 
	 */
	public Integer updateByPrimaryKeySelective(T entity);
	
	/**
	 * @description 通过主键修改 
	 */
	public Integer updateByPrimaryKey(T entity);
	
	/**
	 * @Description 批量修改
	 */
	public Integer updateList(Map params);
	
}
