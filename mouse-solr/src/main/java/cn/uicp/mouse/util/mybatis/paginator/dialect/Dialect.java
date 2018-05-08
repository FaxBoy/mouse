package cn.uicp.mouse.util.mybatis.paginator.dialect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.RowBounds;

import cn.uicp.mouse.util.mybatis.paginator.domain.Order;
import cn.uicp.mouse.util.mybatis.paginator.domain.PageBounds;


/**
 * 
* @ClassName: Dialect 
* @Description: TODO(类似hibernate的dialect，只使用分页部分) 
* @author shil sl166199@163.com 
* @date 2016年8月2日 下午4:43:54 
*
 */
public class Dialect {

	protected MappedStatement mappedStatement;
	protected PageBounds pageBounds;
	protected Object parameterObject;
    protected BoundSql boundSql;
    protected List<ParameterMapping> parameterMappings;
    protected Map<String, Object> pageParameters = new HashMap<String, Object>();

    private String pageSQL;
    private String countSQL;
	
    public Dialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds){
        this.mappedStatement = mappedStatement;
        this.parameterObject = parameterObject;
        this.pageBounds = pageBounds;

        init();
    }
    
    protected void init(){
        boundSql = mappedStatement.getBoundSql(parameterObject);
        parameterMappings = new ArrayList(boundSql.getParameterMappings());
        if(parameterObject instanceof Map){
            pageParameters.putAll((Map)parameterObject);
        }else{
            for (ParameterMapping parameterMapping : parameterMappings) {
                pageParameters.put(parameterMapping.getProperty(),parameterObject);
            }
        }

        StringBuffer bufferSql = new StringBuffer(boundSql.getSql().trim());
        if(bufferSql.lastIndexOf(";") == bufferSql.length()-1){
            bufferSql.deleteCharAt(bufferSql.length()-1);
        }
        String sql = bufferSql.toString();
        pageSQL = sql;
        if(pageBounds.getOrders() != null && !pageBounds.getOrders().isEmpty()){
            pageSQL = getSortString(sql, pageBounds.getOrders());
        }
        if(pageBounds.getOffset() != RowBounds.NO_ROW_OFFSET
                || pageBounds.getLimit() != RowBounds.NO_ROW_LIMIT){
            pageSQL = getLimitString(pageSQL, "__offset", pageBounds.getOffset(), "__limit",pageBounds.getLimit());
        }


        countSQL = getCountString(sql);
    }
    
    public List<ParameterMapping> getParameterMappings(){
        return parameterMappings;
    }

    public Object getParameterObject(){
        return pageParameters;
    }


    public String getPageSQL(){
        return pageSQL;
    }

    protected void setPageParameter(String name, Object value, Class type){
        ParameterMapping parameterMapping = new ParameterMapping.Builder(mappedStatement.getConfiguration(), name, type).build();
        parameterMappings.add(parameterMapping);
        pageParameters.put(name, value);
    }


    public String getCountSQL(){
        return countSQL;
    }

    
    /**
     * 将sql变成分页sql语句
     */
    protected String getLimitString(String sql, String offsetName,int offset, String limitName, int limit) {
        throw new UnsupportedOperationException("paged queries not supported");
    }

    /**
     * 将sql转换为总记录数SQL
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    protected String getCountString(String sql){
    	//先去掉mybatis生成的\n\t,再去掉排序
    	sql = sql.replaceAll("\n|\t"," ").replaceAll("(?i)order\\s+by.*","");
    	//单句进行匹配
    	//String reg = "^select (?:(?!select|from)[\\s\\S])*from";
    	/*可多个匹配 切记临时表不要放在前面 
    	select t0.*,(select xxx from t1) t1,(select xxx from t2) t2 from t0
    	替换为 select count(1) from t0
    	*/
    	String reg = "(?i)select (?:(?!select|from)[\\s\\S])*(\\(select (?:(?!from)[\\s\\S])* from [^\\)]*\\)(?:(?!select|from)[^\\(])*)*(?i)from";
    	//return "select count(1) from (" + sql + ") tmp_count";
    	return sql.replaceFirst(reg, "select count(1) from");
    }
    
    public static void main(String[] args) {
    	String sql ="SELECT s.supplyId,\ts.imgUri,s.stockNum,date_format(s.regTime, '%Y-%m-%d') as regTime,(select id from t2) a "
    			+ "from "
    			+ "ms_supply s force index (sc) "
    			+ "where s.status = 1 "
    			+ "and s.cateId between 'G65' and 'G65ZZZZ' and s.origin like '003-86%'";
    	//单句进行匹配  (?i)不区分大小写
//    	String reg = "^select (?:(?!select|from)[\\s\\S])*from";
    	/*可多个匹配 切记临时表不要放在前面 
    	select t0.*,(select xxx from t1) t1,(select xxx from t2) t2 from t0
    	替换为 select count(1) from t0
    	*/
    	String reg = "(?i)select (?:(?!select|from)[\\s\\S])*(\\(select (?:(?!from)[\\s\\S])* from [^\\)]*\\)(?:(?!select|from)[^\\(])*)*(?i)from";
    	System.out.println(sql.replaceFirst(reg, "select count(1) from"));
	}

    /**
     * 将sql转换为带排序的SQL
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    protected String getSortString(String sql, List<Order> orders){
        if(orders == null || orders.isEmpty()){
            return sql;
        }

        StringBuffer buffer = new StringBuffer("select * from (").append(sql).append(") temp_order order by ");
        for(Order order : orders){
            if(order != null){
                buffer.append(order.toString())
                        .append(", ");
            }

        }
        buffer.delete(buffer.length()-2, buffer.length());
        return buffer.toString();
    }
}
