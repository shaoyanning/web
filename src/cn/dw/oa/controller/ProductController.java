package cn.dw.oa.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.dw.oa.model.Product;

// 取代了ProductServlet, ProductController需要依赖注入: ProductService
@RequestMapping("/product")   // 类似: @WebServlet
@Controller
public class ProductController extends BaseController {
	
	public static void main(String[] args) {
		new File("C:/1.txt");
	}
	
	// 之前servlet通过doGet doPost,此方法需要if判断
	// 如果前端的name,与参数的属性对应,则会自动注入
	@RequestMapping("/save")
	public String save(Product product,MultipartFile pic) {
		// System.out.println(UUID.randomUUID().toString());
		// 1: 获取默认文件名
		String originalFilename = pic.getOriginalFilename();
		String realPath = application.getRealPath("/image/");
		System.out.println(realPath + "" + originalFilename);
		try {
			// 实现文件上传功能
			pic.transferTo(new File(realPath,originalFilename));
			// 文件名添加到数据库中
			product.setImg(originalFilename);
			productService.save(product);
			return "redirect:/query.jsp";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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






