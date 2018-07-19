package cn.dw.oa.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

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
	// web中java支持并发访问,如果是单例模式(一个对象)都需要考虑线程安全问题
//	private String keyword = null;  // 采用session存储每个用户自己的数据

	public ProductServlet() {
		super();
		System.out.println("ProductServlet()...");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	// doPost有两个参数: request：用来封装客户端到服务器端的请求数据
	// response: 封装服务器端返回给客户端的数据
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1: 获取type的值,根据值来判断具体的操作
		String type = request.getParameter("type");
		if(type.equals("save")) {
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
		}else if(type.equals("query")) {
			// 1: 获取前端的数据 (通过前台的表单元素的名称来获取数据)
			// 必须采用每个用户自身的session来存储自己的查询关键字
			HttpSession session = request.getSession();
			System.out.println("query" + session);
			String keyword = request.getParameter("keyword");
			session.setAttribute("keyword", keyword); 
			// 2: 调用Service业务逻辑(Servlet自身不负责任何功能模块的实现)
			List<Product> proList = productService.queryByName(keyword);
			request.setAttribute("proList", proList);
			// java web中有两种跳转: 
			  // 重定向: Redirect(先访问servlet,然后客户端在访问query.jsp)
			  // 转发: forward默认已经添加工程名(只能转发到系统内部资源)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
			// 如果 servlet与jsp页面存在数据的共享则必须使用转发而非重定向
			dispatcher.forward(request, response);
		}else if(type.equals("delete")) {
			String id = request.getParameter("id");
			productService.delete(Integer.parseInt(id));
			// 获取之前的查询关键字,重新查询(自己的session中去获取查询的关键字)
			HttpSession session = request.getSession();
			System.out.println("delete" + session);
			String keyword  = (String)session.getAttribute("keyword");
			productService.queryByName(keyword);
			List<Product> proList = productService.queryByName(keyword);
			request.setAttribute("proList", proList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
			dispatcher.forward(request, response);
		}else if(type.equals("getById")) {
			// 1: 获取前端的数据
			Product product = productService.getById(Integer.parseInt(request.getParameter("id")));
			// 2：根据id查询当前商品对象,如果有缓存则自动缓存获取(查询的对象存储在request中)
			request.setAttribute("product", product);
			// 3: 转发到update.jsp页面
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
			dispatcher.forward(request, response);
		}else if(type.equals("update")) {
			Product product = new Product();
			// ctrl + 2 shift+alt+A ctrl + shift + F
			product.setName(request.getParameter("name"));
			product.setRemark(request.getParameter("remark"));
			product.setPrice(Double.parseDouble(request.getParameter("price")));
			product.setId(Integer.parseInt(request.getParameter("id")));
			productService.update(product);
			response.sendRedirect("/web/query.jsp");
		}
	}

}




