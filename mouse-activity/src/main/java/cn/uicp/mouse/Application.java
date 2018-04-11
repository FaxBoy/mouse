package cn.uicp.mouse;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cn.uicp.mouse.dao.CompRepository;
import cn.uicp.mouse.dao.PersonRepository;
import model.Comp;
import model.Person;
import cn.uicp.mouse.service.ActivitiService;

@SpringBootApplication
@ComponentScan("cn.uicp.mouse")
@EnableJpaRepositories("cn.uicp.mouse.dao")
@EntityScan("model")
public class Application {

	@Autowired
	private CompRepository compRepository;
	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot:");

		 String[] beanNames = ctx.getBeanDefinitionNames();
		 Arrays.sort(beanNames);
		 for (String beanName : beanNames) {
		 System.out.println(beanName);
		 }

	}

	// 初始化模拟数据
//	@Bean
//	public CommandLineRunner init(final ActivitiService myService) {
//		return new CommandLineRunner() {
//			public void run(String... strings) throws Exception {
//				if (personRepository.findAll().size() == 0) {
//					personRepository.save(new Person("wtr"));
//					personRepository.save(new Person("wyf"));
//					personRepository.save(new Person("admin"));
//				}
//				if (compRepository.findAll().size() == 0) {
//					Comp group = new Comp("great company");
//					compRepository.save(group);
//					Person admin = personRepository.findByPersonName("admin");
//					Person wtr = personRepository.findByPersonName("wtr");
//					admin.setComp(group);
//					wtr.setComp(group);
//					personRepository.save(admin);
//					personRepository.save(wtr);
//				}
//			}
//		};
//	}

	/**
	 * @Description TODO(tomcat 配置)
	 * @author shil <sl166199@163.com>
	 * @date 2017/11/23 16:37
	 * @param []
	 * @return org.springframework.boot.context.embedded.EmbeddedServletContainerFactory
	 */
	 @Bean
	 public EmbeddedServletContainerFactory servletFactor(){
	 TomcatEmbeddedServletContainerFactory tomcatFactory =
	 new TomcatEmbeddedServletContainerFactory();
	 tomcatFactory.setPort(8011);
	 tomcatFactory.setSessionTimeout(10, TimeUnit.SECONDS);
	 return tomcatFactory;
	 }

}
