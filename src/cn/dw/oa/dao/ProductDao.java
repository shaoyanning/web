package cn.dw.oa.dao;

import java.util.List;

import cn.dw.oa.model.Product;

public interface ProductDao {

	List<Product> queryByName(String keyword);

	Product getById(int id);

	int save(Product product);

	int update(Product product);

	int delete(int id);
	// 默认方法: 接口支持方法实现
	default public void show() {
		System.out.println("show()......");
	}
	
	

}