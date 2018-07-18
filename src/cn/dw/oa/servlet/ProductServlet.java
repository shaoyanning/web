package cn.dw.oa.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.dw.oa.model.Product;
import cn.dw.oa.service.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
// jsp --> servlet --> service --> dao --> db
@WebServlet("/ProductServlet") // 访问当前Servlet的地址
public class ProductServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ProductServiceImpl productService=new ProductServiceImpl();

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1: 获取前端的数据 (通过前台的表单元素的名称来获取数据)
		String keyword = request.getParameter("keyword");
		// 2: 调用Service业务逻辑(Servlet自身不负责任何功能模块的实现)
		List<Product> proList = productService.queryByName(keyword);
		HttpSession session = request.getSession();
		session.setAttribute("proList",proList);
		// 3: 跳转到下一个页面(服务器给客户端的响应)
		response.sendRedirect("/web/query.jsp");
	}

	// doPost有两个参数: request：用来封装客户端到服务器端的请求数据
	// response: 封装服务器端返回给客户端的数据
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request:" + request);
		System.out.println("doPost..............");
		// 1: 获取前端的数据 (通过前台的表单元素的名称来获取数据)
		Product product = new Product();
		// ctrl + 2 shift+alt+A ctrl + shift + F
		product.setName(request.getParameter("name"));
		product.setRemark(request.getParameter("remark"));
		product.setPrice(Double.parseDouble(request.getParameter("price")));
		// 2: 调用Service业务逻辑(Servlet自身不负责任何功能模块的实现)
		productService.save(product);
		// 3: 跳转到下一个页面(服务器给客户端的响应)
		response.sendRedirect("/web/query.jsp");
	}

}
