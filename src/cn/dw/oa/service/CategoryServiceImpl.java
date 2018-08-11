package cn.dw.oa.service;

import java.util.List;

import cn.dw.oa.dao.CategoryDao;
import cn.dw.oa.model.Category;

public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	

	@Override
	public List<Category> queryByName(String keyword) {
		// TODO Auto-generated method stub
		return categoryDao.queryByName("%" + keyword + "%");
	}

}
