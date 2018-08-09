package cn.dw.oa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dw.oa.model.Category;
import cn.dw.oa.model.Product;
import cn.dw.oa.service.ProductService;

// 取代了ProductServlet, ProductController需要依赖注入: ProductService
@RequestMapping("/category")   // 类似: @WebServlet
public class CategoryController extends BaseController {
	
	@RequestMapping("/ajax")
	@ResponseBody  // 需要添加相应的json包
	public Object ajax(String name,String pass) {
		System.out.println("name:" + name);
		System.out.println("pass:" + pass);
		// 此处模拟从后台获取商品类别数据
		List<Category> cList = new ArrayList<Category>();
		cList.add(new Category(1, "家用电器"));
		cList.add(new Category(2, "儿童玩具"));
		return cList;  // 会自动转化为json格式
	}

}






