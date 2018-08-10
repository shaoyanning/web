package cn.dw.oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dw.oa.model.Product;

public interface ProductDao {

	public List<Product> queryByName(@Param("keyword") String name, @Param("start") int start, @Param("size") int size);

	public Product getById(int id);

	public int save(Product product);

	public int update(Product product);

	public int delete(int id);

}