package cn.dw.oa.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.dw.oa.model.Category;
import cn.dw.oa.service.CategoryService;

// ServletContextListener:监听器,它在项目启动的时候创建,项目关闭的时候销毁(Listener > Filter > servlet)
public class InitDataListener implements ServletContextListener {
	
	private CategoryService categoryService;
	
	private ApplicationContext context;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 配置文件加载xml中定义bean都会被创建
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml","spring-public.xml");
		// TODO Auto-generated method stub
		System.out.println("contextInitialized..............");
		// 从application内置对象中获取spring的配置文件
		ServletContext application = event.getServletContext();
		System.out.println("application:" + application);
		// WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE
		context = WebApplicationContextUtils.getWebApplicationContext(application);
		categoryService = context.getBean("cs",CategoryService.class);
		List<Category> catList = categoryService.queryByName("");
		application.setAttribute("caList", catList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("contextDestroyed..............");
	}

	

}
