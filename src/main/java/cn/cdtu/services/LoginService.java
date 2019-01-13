package cn.cdtu.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.User;
import cn.cdtu.exceptions.LoginException;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
@Transactional
@Service
public class LoginService {
	@Autowired
	UserService userService;
	
	/**
	 * 用户登录
	 * @param user 尝试登录的用户
	 * @return 是否登录成功
	 */
	public boolean login(User user)throws LoginException{
		User db_user = userService.findUserByName(user.getName());
		
		if(db_user == null)
			throw new LoginException("用户不存在");
		
		return db_user.getPassword().equals(user.getPassword()) ? true : false;
	}
	
	/**
	 * 
	 * @param name 待查找用户的用户名
	 * @return 待查找的用户
	 */
	public User findUserByName(String name){
		System.out.println(userService.findUserByName(name));
		return userService.findUserByName(name);
	}
}
