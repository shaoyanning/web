package cn.dw.oa.service;

import java.util.List;

import cn.dw.oa.model.Category;

public interface CategoryService {

	List<Category> queryByName(String keyword);
}