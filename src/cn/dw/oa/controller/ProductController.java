package cn.dw.oa.controller;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.dw.oa.model.Product;
import cn.dw.oa.service.ProductService;

// 取代了ProductServlet, ProductController需要依赖注入: ProductService
@RequestMapping("/product")   // 类似: @WebServlet
public class ProductController {
	
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 所有的request,session,application都可以通过注解自动按照类型注入, 需要在xml中配置注解扫描
	@Resource
	private HttpServletRequest request;
	@Resource
	private HttpSession session;
	
	// 之前servlet通过doGet doPost,此方法需要if判断
	// 如果前端的name,与参数的属性对应,则会自动注入
	@RequestMapping("/save")
	public String save(Product product) {
		productService.save(product);
		return "redirect:/query.jsp";
	}
	
	public void update(Product product) {
		
	}
}
