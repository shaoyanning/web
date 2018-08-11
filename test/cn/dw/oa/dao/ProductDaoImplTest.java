package cn.dw.oa.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dw.oa.model.Product;
import cn.dw.oa.service.ProductService;

public class ProductDaoImplTest {
	// 面向接口编程
	private static ProductService productService = null;
	private static ApplicationContext context = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("测试方法之前会执行,一般用来初始化资源......");
		context = new ClassPathXmlApplicationContext("spring-bean.xml","spring-public.xml");
//		daoImpl = (ProductDaoImpl)context.getBean("pd");
		productService = context.getBean("ps",ProductService.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("测试方法之后执行,一般用来销毁资源......");
		productService = null;
	}

	@Test
	public void testQueryByName() {
		for (Product temp : productService.queryByName("")) {
			System.out.println(temp);
		}
	}

	@Test
	public void testGetById() {
		System.out.println(productService.getById(1));
	}

	@Test
	public void testSave() {
		System.out.println("save.....");
		Product product = new Product();
		product.setName("Iphone 16");
		product.setPrice(6666.14);
		product.setRemark("我是备注");
		productService.save(product);
//		Integer.parseInt("xxx");
		System.out.println("--------------------");
	}

	@Test
	public void testUpdateProduct() {
		Product product = new Product();
		product.setName("Iphone 10");
		product.setPrice(6666.14);
		product.setRemark("我是备注");
		product.setId(2);
		productService.update(product);
	}

	@Test
	public void testDelete() {
		productService.delete(4);
	}

}
