package cn.uicp.mouse.util.mybatis.jdbc;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * <p>Description: Jdbc持久层委托代理实现类，具备翻页功能。</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * @author Jxt
 * @version 1.0
 * @date 2008.12.2
 */
public class JdbcManagerImp implements JdbcManager {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcManagerImp() {
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return Connection
	 * @throws DataAccessException
	 */
	public Connection getConnection() throws DataAccessException {
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
		} catch (SQLException se) {
			throw jdbcTemplate.getExceptionTranslator().translate("无法获取数据库连接.", "", se);
		}
		return conn;
	}

	/**
	 * 执行一条SQL
	 * 
	 * @param sql
	 * @throws DataAccessException
	 */
	public void execute(String sql) throws DataAccessException {
		jdbcTemplate.execute(sql);
	}

	/**
	 * 执行不带参数的SQL查询，返回第一个字段值的整数
	 * 
	 * @param sql
	 * @return int
	 * @throws DataAccessException
	 */
	public int queryForInt(String sql) throws DataAccessException {
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	/**
	 * 执行带参数的SQL查询，执行前按照顺序替换，返回第一个字段值的整数
	 * 
	 * @param sql
	 * @param args
	 * @return int
	 * @throws DataAccessException
	 */
	public int queryForInt(String sql, Object...args)
			throws DataAccessException {
		return jdbcTemplate.queryForObject(sql, Integer.class, args);
	}

	/**
	 * 执行不带参数的SQL查询，返回Map
	 * 
	 * @param sql
	 * @return Map
	 * @throws SQLException
	 */
	public Map<?,?> queryForMap(String sql) throws DataAccessException {
		return jdbcTemplate.queryForMap(sql);
	}

	/**
	 * 执行带参数的SQL查询，返回Map
	 * 
	 * @param sql
	 * @param args
	 * @return Map
	 * @throws SQLException
	 */
	public Map<?,?> queryForMap(String sql, Object...args) throws DataAccessException {
		return jdbcTemplate.queryForMap(sql, args);
	}

	/**
	 * 执行不带参数的SQL查询，返回Map的列表
	 * 
	 * @param sql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> queryForList(String sql) throws DataAccessException {
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 执行带参数的SQL查询，执行前按照顺序替换，返回Map的列表
	 * 
	 * @param sql
	 * @param args
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> queryForList(String sql, Object...args)
			throws DataAccessException {
		return jdbcTemplate.queryForList(sql, args);
	}

	
	/**
	 * 执行一条SQL
	 * 
	 * @param sql
	 * @return int
	 * @throws DataAccessException
	 */
	public int update(String sql) throws DataAccessException {
		return jdbcTemplate.update(sql);
	}

	/**
	 * 执行带参数的SQL
	 * 
	 * @param sql
	 * @param args
	 * @return int
	 * @throws DataAccessException
	 */
	public int update(String sql, Object...args) throws DataAccessException {
		return jdbcTemplate.update(sql, args);
	}

	/**
	 * 批量提交SQL
	 * 
	 * @param sql
	 * @return int[]
	 * @throws DataAccessException
	 */
	public int[] batchUpdate(String[] sql) throws DataAccessException {
		return jdbcTemplate.batchUpdate(sql);
	}

}