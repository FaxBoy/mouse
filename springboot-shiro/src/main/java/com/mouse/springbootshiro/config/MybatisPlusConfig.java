/**  
 * All rights Reserved, Designed By www.wavenet.com.cn
 * @Title:  MybatisPlusConfig.java   
 * @Package com.wavenet.config   
 * @Description:Mybatis插件配置
 * @author: 刘仪轩 
 * @date:   2019年3月8日 上午10:00:13   
 * @version V1.0 
 * @Copyright: 2019 www.wavenet.com.cn Inc. All rights reserved. 
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.mouse.springbootshiro.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ClassName:  MybatisPlusConfig   
 * @Description: 配置MyBatis插件  
 * @author: 刘仪轩
 * @date:   2019年3月8日 上午9:59:14     
 * @Copyright: 2019 www.wavenet.com.cn Inc. All rights reserved. 
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@Configuration
public class MybatisPlusConfig {
    /**   
     * @Title: mapperScannerConfigurer   
     * @Description: 扫描mapper路径
     * @param:      
     * @return: MapperScannerConfigurer      
     * @throws   
     */ 
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.mouse.springbootshiro.mapper*");
        return scannerConfigurer;
    }
    /**   
     * @Title: performanceInterceptor   
     * @Description: 格式化SQL语句
     * @param:       
     * @return: PerformanceInterceptor      
     * @throws   
     */ 
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        interceptor.setProperties(properties);
        return interceptor;
    }
    /**   
     * @Title: paginationInterceptor   
     * @Description: Mybatis-plus分页插件  
     * @param: @return      
     * @return: PaginationInterceptor      
     * @throws   
     */ 
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
