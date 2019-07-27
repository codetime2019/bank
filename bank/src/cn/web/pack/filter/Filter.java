package cn.web.pack.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "Filter", urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {

	@Override
	public void destroy() {

		System.out.println("Filter.destroy()");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter.init()");
	}

}
