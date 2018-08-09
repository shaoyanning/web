package cn.dw.oa.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.dw.oa.service.ProductService;

// 用来存储公共的代码
public class BaseController {

	protected ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 所有的request,session,application都可以通过注解自动按照类型注入, 需要在xml中配置注解扫描
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpSession session;

}
