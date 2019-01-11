package com.mouse.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.Request;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Filter;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName LoginFilter
 * @Description 自定义loginfilter 实现 com.netflix.zuul.ZuulFilter
 * @date 2018/9/14 11:02
 */
@Component
public class LoginFilter extends ZuulFilter {

    /**
     * @Description TODO(过滤起类型)
     * @author shil <sl166199@163.com>
     * @date 2019/1/11 11:07
     * @param []
     * @return java.lang.String
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**  
     * @Description TODO(顺序,越小越先执行)
     * @author shil <sl166199@163.com>  
     * @date 2019/1/11 11:08
     * @param []  
     * @return int  
     */  
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    /**
     * @Description TODO(过滤器是否生效)
     * @author shil <sl166199@163.com>
     * @date 2019/1/11 11:11
     * @param []
     * @return boolean
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println(request.getRequestURI());// /apigateway-order/api/v1/order/save
        System.out.println(request.getRequestURL());// http://172.18.1.142:9000/apigateway-order/api/v1/order/save

        //ACL
        if("/apigateway-order/api/v1/order/save".equalsIgnoreCase(request.getRequestURI())){
            return true;
        }
        return false;
    }

    /**
     * @Description TODO(过滤的业务逻辑)
     * @author shil <sl166199@163.com>
     * @date 2019/1/11 11:11
     * @param []
     * @return java.lang.Object
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("*********拦截器**********");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("token");

        //JWT 解密
        if(StringUtils.isBlank(token)){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
