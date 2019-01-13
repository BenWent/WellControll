package cn.cdtu.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.cdtu.beans.User;
import cn.cdtu.exceptions.LoginException;
import cn.cdtu.services.LoginService;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login.action",method={RequestMethod.POST})
	public void login(User user,HttpSession session,HttpServletRequest req
			,HttpServletResponse resp)throws IOException{
		
		try {
			if(loginService.login(user)){
				session.setAttribute("user",loginService.findUserByName(user.getName()));
				
				resp.getWriter().write("{" 
							+ "\"nextPath\":" + "\"" + req.getContextPath() + "/index.jsp" + "\""
						+"}");
			}else{
				resp.getWriter().write("{"
							+ "\"mismatch\":" + "\"用户名或密码错误\"" 
							+",\"user_name\":" + "\"" + user.getName() + "\""
						+ "}");
			}
		} catch (LoginException e) {
			e.printStackTrace();
			
			resp.getWriter().write("{"
					+ "\"userError\":" + "\"" + e.getMessage() +"\"" 
					+",\"user_name\":" + "\"" + user.getName() + "\""
				+ "}");
		}
	}
	
	@RequestMapping(value="/loginOut.action",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loginOut(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.addObject("user_name",((User)session.getAttribute("user")).getName());
		mav.setViewName("/pages/login.jsp");
		
		session.removeAttribute("user");
		
		return mav;
	}
}
