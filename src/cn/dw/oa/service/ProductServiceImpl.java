package cn.dw.oa.service;

import java.util.List;

import cn.dw.oa.dao.ProductDao;
import cn.dw.oa.dao.ProductDaoImpl;
import cn.dw.oa.model.Product;

// 此类是业务逻辑类,主要完成项目业务逻辑,并且调用数据访问层
public class ProductServiceImpl implements ProductService {
	// bootstrap
	private ProductDao productDao = null;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	/* (non-Javadoc)
	 * @see cn.dw.oa.service.ProductService#queryByName(java.lang.String)
	 */
	@Override
	public List<Product> queryByName(String keyword) {
		return productDao.queryByName(keyword);
	}

	/* (non-Javadoc)
	 * @see cn.dw.oa.service.ProductService#getById(int)
	 */
	@Override
	public Product getById(int id) {
		return productDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see cn.dw.oa.service.ProductService#save(cn.dw.oa.model.Product)
	 */
	@Override
	public int save(Product product) {
		int result =productDao.save(product);
		// 有些时候一个service中可能执行多个dao操作
		return result;
	}

	/* (non-Javadoc)
	 * @see cn.dw.oa.service.ProductService#update(cn.dw.oa.model.Product)
	 */
	@Override
	public int update(Product product) {
		return productDao.update(product);
	}

	/* (non-Javadoc)
	 * @see cn.dw.oa.service.ProductService#delete(int)
	 */
	@Override
	public int delete(int id) {
		return productDao.delete(id);
	}

}
