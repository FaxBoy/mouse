package cn.uicp.mouse.util.mybatis.paginator.dialect;

import org.apache.ibatis.mapping.MappedStatement;

import cn.uicp.mouse.util.mybatis.paginator.domain.PageBounds;



public class OracleDialect extends Dialect{

    public OracleDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
        super(mappedStatement, parameterObject, pageBounds);
    }

    protected String getLimitString(String sql, String offsetName,int offset, String limitName, int limit) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		if (offset > 0) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		}
		else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
			pagingSelect.append(" ) row_ ) t_temp where rownum_ <= ? and rownum_ > ?");
//			pagingSelect.append(" ) row_ where rownum <= ?) t_temp where rownum_ > ?");
            setPageParameter("__offsetEnd",offset+limit,Integer.class);
            setPageParameter(offsetName,offset,Integer.class);
		}
		else {
			pagingSelect.append(" ) t_temp where rownum <= ?");
            setPageParameter(limitName,limit,Integer.class);
		}

		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		
		return pagingSelect.toString();
	}

}
