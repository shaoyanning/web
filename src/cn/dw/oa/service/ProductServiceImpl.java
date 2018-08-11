package cn.dw.oa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.dw.oa.dao.ProductDao;
import cn.dw.oa.model.Product;

// 此类是业务逻辑类,主要完成项目业务逻辑,并且调用数据访问层
@Service("ps")  // 注解中,只有value属性可以省略
public class ProductServiceImpl implements ProductService {
	
	public ProductServiceImpl() {
		System.out.println("ProductServiceImpl()...........");
	}
	
	@Resource  // 配置name则必须按照名称注入,如果没有配置name则默认按照属性名注入,如果属性没有找到则按照类型注入
	private ProductDao productDao = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.service.ProductService#queryByName(java.lang.String)
	 */
	@Override
	public List<Product> queryByName(String keyword) {
		int page = 1; int size = 5;
		return productDao.queryByName("%" + keyword + "%",(page-1)*size,size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.service.ProductService#getById(int)
	 */
	@Override
	public Product getById(int id) {
		return productDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.service.ProductService#save(cn.dw.oa.model.Product)
	 */
	@Override
	public int save(Product product) {
		int result = productDao.save(product);
		// 有些时候一个service中可能执行多个dao操作
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.service.ProductService#update(cn.dw.oa.model.Product)
	 */
	@Override
	public int update(Product product) {
		return productDao.update(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.service.ProductService#delete(int)
	 */
	@Override
	public int delete(int id) {
		return productDao.delete(id);
	}

}
