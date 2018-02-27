package cn.uicp.mouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
        
        
    }


    /**
     * @Description TODO(tomcat 配置)
     * @author shil <sl166199@163.com>
     * @date 2017/11/23 16:37
     * @param []
     * @return org.springframework.boot.context.embedded.EmbeddedServletContainerFactory
     */
//    @Bean
//    public EmbeddedServletContainerFactory servletFactor(){
//        TomcatEmbeddedServletContainerFactory tomcatFactory =
//                new TomcatEmbeddedServletContainerFactory();
//        tomcatFactory.setPort(8011);
//        tomcatFactory.setSessionTimeout(10, TimeUnit.SECONDS);
//        return tomcatFactory;
//    }


}
