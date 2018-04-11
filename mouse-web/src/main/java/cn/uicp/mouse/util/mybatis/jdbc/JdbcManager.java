package cn.uicp.mouse.util.mybatis.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: Jdbc持久层委托代理接口，具备翻页功能。</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: jxt</p>
 * @author Jxt
 * @version 1.0
 * @date 2008.12.2
 */
public interface JdbcManager {
    /**
     * 获取数据库连接
     * @return Connection
     * @throws Exception
     */
    public Connection getConnection() throws Exception;
    /**
     * 执行一条SQL
     * @param sql
     * @throws Exception
     */
    public void execute(String sql) throws Exception;

    /**
     * 执行不带参数的SQL查询，返回第一个字段值的整数
     * @param sql
     * @return int
     * @throws Exception
     */
    public int queryForInt(String sql) throws Exception;

    /**
     * 执行带参数的SQL查询，执行前按照顺序替换，返回第一个字段值的整数
     * @param sql
     * @param args
     * @return int
     * @throws Exception
     */
    public int queryForInt(String sql, Object...args) throws Exception;
    
    /**
	 * 执行不带参数的SQL查询，返回Map
	 * 
	 * @param sql
	 * @return Map
	 * @throws SQLException
	 */
	public Map<?,?> queryForMap(String sql) throws Exception;
	
	/**
	 * 执行带参数的SQL查询，返回Map
	 * 
	 * @param sql
	 * @param args
	 * @return Map
	 * @throws SQLException
	 */
	public Map<?,?> queryForMap(String sql,Object...args) throws Exception;

    /**
     * 执行不带参数的SQL查询，返回Map的列表
     * @param sql
     * @return List
     * @throws Exception
     */
    public List<?> queryForList(String sql) throws Exception;
    
    /**
     * 执行带参数的SQL查询，执行前按照顺序替换，返回Map的列表
     * @param sql
     * @param args
     * @return List
     * @throws Exception
     */
    public List<?> queryForList(String sql, Object...args) throws Exception;
    
    /**
     * 执行一条SQL
     * @param sql
     * @return int
     * @throws Exception
     */
    public int update(String sql) throws Exception;

    /**
     * 执行带参数的SQL
     * @param sql
     * @param args
     * @return int
     * @throws Exception
     */
    public int update(String sql, Object...args) throws Exception;

    /**
     * 批量提交SQL
     * @param sql
     * @return int[]
     * @throws Exception
     */
    public int[] batchUpdate(String[] sql) throws Exception;
}
