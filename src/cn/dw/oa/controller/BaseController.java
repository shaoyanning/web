package cn.dw.oa.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import cn.dw.oa.service.CategoryService;
import cn.dw.oa.service.ProductService;

// 用来存储公共的代码
@Controller
public class BaseController {
	
	@Resource(name="ps")
	protected ProductService productService;
	@Resource(name="cs")
	protected CategoryService categoryService;

	// 所有的request,session,application都可以通过注解自动按照类型注入, 需要在xml中配置注解扫描
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpSession session;
	@Resource
	protected ServletContext application;
	

}
