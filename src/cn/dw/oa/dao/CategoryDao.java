package cn.dw.oa.dao;

import java.util.List;

import cn.dw.oa.model.Category;

public interface CategoryDao {

	public List<Category> queryByName(String keyword);
}
