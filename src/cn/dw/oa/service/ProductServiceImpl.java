package cn.dw.oa.service;

import java.util.List;

import cn.dw.oa.dao.ProductDao;
import cn.dw.oa.dao.ProductDaoImpl;
import cn.dw.oa.model.Product;

// 此类是业务逻辑类,主要完成项目业务逻辑,并且调用数据访问层
public class ProductServiceImpl {
	// bootstrap
	private ProductDao productDao = new ProductDaoImpl();
	
	public List<Product> queryByName(String keyword) {
		return productDao.queryByName(keyword);
	}

	public Product getById(int id) {
		return productDao.getById(id);
	}

	public int save(Product product) {
		return productDao.save(product);
	}

	public int update(Product product) {
		return productDao.update(product);
	}

	public int delete(int id) {
		return productDao.delete(id);
	}

}
