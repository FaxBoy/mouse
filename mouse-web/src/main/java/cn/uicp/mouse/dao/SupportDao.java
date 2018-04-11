package cn.uicp.mouse.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.uicp.mouse.util.mybatis.paginator.domain.PageBounds;



/**
 * 
* @ClassName: SupportDao T表示实体类；TD表示主键类型
* @Description: TODO(Dao层公共部分) 
* @author shil
* @date 2017年4月25日 下午1:11:58 
* 
* @param <T>
* @param <ID>
 */
public interface SupportDao<T, ID extends Serializable> {

	/**
	 * 
	* @Title: load 
	* @Description: TODO(通过主键查询model，TD是主键的类型) 
	* @param @param id
	* @param @return    设定文件 
	* @return T    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:07:15 
	* @throws
	 */
	T load(ID id);

	/**
	 * 
	* @Title: select 
	* @Description: TODO(通过多条件查询集合) 
	* @param @param params
	* @param @return    设定文件 
	* @return List    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:07:27 
	* @throws
	 */
	List select(Map params);
	

	/**
	 * 
	* @Title: selectPage 
	* @Description: TODO(分页查询) 
	* @param @param params
	* @param @param pageBounds
	* @param @return    设定文件 
	* @return List    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:07:37 
	* @throws
	 */
	List selectPage(Map params,PageBounds pageBounds);

	/**
	 * 
	* @Title: selectCountByMap 
	* @Description: TODO(多条件查询条数) 
	* @param @param params
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:07:49 
	* @throws
	 */
	Integer selectCountByMap(Map params);

	/**
	 * 
	* @Title: deleteByPrimaryKey 
	* @Description: TODO(通过主键删除 ，TD是主键的类型) 
	* @param @param id
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:08:01 
	* @throws
	 */
	Integer deleteByPrimaryKey(ID id);
	
	Integer deleteByPrimaryKey(T entity);
	
	/**
	 * 
	* @Title: deleteList 
	* @Description: TODO(批量删除) 
	* @param @param list
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:08:22 
	* @throws
	 */
	Integer deleteList(List list);

	/**
	 * 
	* @Title: deleteByMap 
	* @Description: TODO(多条件删除) 
	* @param @param params
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:08:35 
	* @throws
	 */
	Integer deleteByMap(Map params);

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(通过实体对象插入) 
	* @param @param entity
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:08:45 
	* @throws
	 */
	Integer insert(T entity);
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(通过Map对象插入) 
	* @param @param params
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:09:02 
	* @throws
	 */
	Integer insert(Map params);

	/**
	 * 
	* @Title: insertSelective 
	* @Description: TODO(多条件插入) 
	* @param @param entity
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:09:12 
	* @throws
	 */
	Integer insertSelective(T entity);
	
	

	/**
	 * 
	* @Title: selectByPrimaryKey 
	* @Description: TODO(通过主键查询实体对象，TD是主键的类型) 
	* @param @param id
	* @param @return    设定文件 
	* @return T    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:09:23 
	* @throws
	 */
	T selectByPrimaryKey(ID id);
	
	T selectByPrimaryKey(T entity);

	/**
	 * 
	* @Title: updateByPrimaryKeySelective 
	* @Description: TODO(多条件修改) 
	* @param @param entity
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:09:34 
	* @throws
	 */
	Integer updateByPrimaryKeySelective(T entity);

	/**
	 * 
	* @Title: updateByPrimaryKey 
	* @Description: TODO(通过主键修改) 
	* @param @param entity
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:09:43 
	* @throws
	 */
	Integer updateByPrimaryKey(T entity);
	
	/**
	 * 
	* @Title: updateList 
	* @Description: TODO(批量修改啊) 
	* @param @param params
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author shil 
	* @date 2017年4月25日 下午1:09:56 
	* @throws
	 */
    Integer updateList(Map params);
    
}
