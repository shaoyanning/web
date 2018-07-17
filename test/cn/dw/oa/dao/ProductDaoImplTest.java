package cn.dw.oa.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.dw.oa.model.Product;

public class ProductDaoImplTest {
	
	private static ProductDaoImpl daoImpl = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("测试方法之前会执行,一般用来初始化资源......");
		daoImpl =new ProductDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("测试方法之后执行,一般用来销毁资源......");
		daoImpl = null;
	}

	@Test
	public void testQueryByName() {
		for (Product temp : daoImpl.queryByName("")) {
			System.out.println(temp);
		}
	}

	@Test
	public void testGetById() {
//		System.out.println(daoImpl.getById(1));
		try {
		Integer.parseInt("xxx");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testSave() {
		System.out.println("save.....");
		Product product = new Product();
		product.setName("Iphone 10");
		product.setPrice(6666.14);
		product.setRemark("我是备注");
		daoImpl.save(product);
	}

	@Test
	public void testUpdateProduct() {
		Product product = new Product();
		product.setName("Iphone 10");
		product.setPrice(6666.14);
		product.setRemark("我是备注");
		product.setId(2);
		daoImpl.update(product);
	}

	@Test
	public void testDelete() {
		daoImpl.delete(4);
	}

}
