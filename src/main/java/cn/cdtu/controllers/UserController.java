package cn.cdtu.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cdtu.beans.User;
import cn.cdtu.services.UserService;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
@RequestMapping(value="/user",method={RequestMethod.POST})
@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/insertUser.action")
	public void insertUser(User user,HttpServletResponse resp)throws IOException{
		System.out.println("插入用户");
		
		resp.getWriter().write(userService.insertUser(user));;
	}
	
	@RequestMapping("/removeUserByName.action")
	public void removeUserByName(String name,HttpServletResponse resp)throws IOException{
		System.out.println("删除用户");
		
		resp.getWriter().write(userService.removeUserByName(name));;
	}
	
	@RequestMapping("/updateUser.action")
	public void updateUser(User user,HttpServletResponse resp)throws IOException{
		System.out.println("更新用户信息");
		
		resp.getWriter().write(userService.updateUser(user));
	}
	
	@RequestMapping("/findAllUsers.action")
	public void findAllUsers(HttpServletResponse resp)throws IOException{
		System.out.println("查询所有的用户信息");
		
		resp.getWriter().write(userService.findAllUsers());
	}
}
