package cn.dw.oa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dw.oa.dao.CategoryDao;
import cn.dw.oa.model.Category;

@Service("cs")
public class CategoryServiceImpl implements CategoryService {
	@Resource(name = "categoryDao") // ref="categoryDao"
	private CategoryDao categoryDao;
	

	@Override
	public List<Category> queryByName(String keyword) {
		// TODO Auto-generated method stub
		return categoryDao.queryByName("%" + keyword + "%");
	}

}
