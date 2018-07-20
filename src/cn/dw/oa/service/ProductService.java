package cn.dw.oa.service;

import java.util.List;

import cn.dw.oa.model.Product;

public interface ProductService {

	List<Product> queryByName(String keyword);

	Product getById(int id);

	int save(Product product);

	int update(Product product);

	int delete(int id);

}