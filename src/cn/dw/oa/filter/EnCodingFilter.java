package cn.dw.oa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// 项目默认是不能识别过滤器,除非通过注解配置
@WebFilter(urlPatterns="/*")  // 过滤所有请求
public class EnCodingFilter implements Filter {
	
	public EnCodingFilter() {
		System.out.println("EnCodingFilter()................");
	}

	@Override
	public void destroy() {
	}

	@Override  // 用来实现过滤处理
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter.....");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 跳转到目标页面
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
