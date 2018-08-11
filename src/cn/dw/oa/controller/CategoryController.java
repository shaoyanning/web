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
		return null;
	}

}






