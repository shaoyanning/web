package cn.dw.oa.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dw.oa.model.Product;
import cn.dw.oa.service.ProductService;

// 取代了ProductServlet, ProductController需要依赖注入: ProductService
@RequestMapping("/product")   // 类似: @WebServlet
public class ProductController extends BaseController {
	
	// 之前servlet通过doGet doPost,此方法需要if判断
	// 如果前端的name,与参数的属性对应,则会自动注入
	@RequestMapping("/save")
	public String save(Product product) {
		productService.save(product);
		return "redirect:/query.jsp";
	}
	@RequestMapping("/update")
	public String update(Product product) {
		productService.update(product);
		return "redirect:/query.jsp";
	}
	
	@RequestMapping("/getById")
	public String getById(Integer id) {
		Product product = productService.getById(id);
		request.setAttribute("product", product);
		// 共享request数据,因此采用转发
		return "forward:/update.jsp";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id) {
		productService.delete(id);
		String keyword  = (String)session.getAttribute("keyword");
		List<Product> proList = productService.queryByName(keyword);
		request.setAttribute("proList", proList);
		// 共享request数据,因此采用转发
		return "forward:/query.jsp";
	}
	
	@RequestMapping("/query")
	public String query(String keyword) {
		session.setAttribute("keyword", keyword); 
		List<Product> proList = productService.queryByName(keyword);
		request.setAttribute("proList", proList);
		return "forward:/query.jsp";
	}
}






