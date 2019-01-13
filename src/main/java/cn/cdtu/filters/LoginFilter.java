package cn.cdtu.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if(request.getHeader("referer") == null){
			if(request.getSession().getAttribute("user") == null){
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}else if(request.getRequestURI().endsWith(request.getServletContext() + "/")
				|| request.getRequestURI().endsWith("/login.action")){
			request.getRequestDispatcher("login.action").forward(request, response);
		}else if(request.getSession().getAttribute("user") == null){
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}else{
			filterChain.doFilter(request, response);
		}
	}

}
