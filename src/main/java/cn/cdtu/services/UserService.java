package cn.cdtu.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.User;
import cn.cdtu.mappers.UserMapper;
import cn.cdtu.utils.JsonConverter;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
@Transactional
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 查找指定用户名的用户信息
	 * @param name 指定的用户名
	 * @return 查找到的用户
	 */
	public User findUserByName(String name){
		User user = userMapper.findUserByName(name);
		
		return user;
	}
	
	/**
	 * 新建一个用户
	 * @param user 新建的用户信息
	 * @return 新建用户信息对象的dataTables 格式的JSON字符串
	 */
	public String insertUser(User user){
		//如果新建的用户给定的用户已经存在，则新建用户失败
		if(findUserByName(user.getName()) != null)
			return JsonConverter.EMPTY_JSON_STRING;//错误的做法，但不想改了
		
		userMapper.insertUser(user);
		return JsonConverter.dataTablesJsonFormat(user);
	}
	
	/**
	 * 删除指定的用户
	 * @param name 指定用户的姓名
	 * @return dataTables 格式的JSON字符串
	 */
	public String removeUserByName(String name){
		userMapper.removeUserByName(name);
		
		return JsonConverter.EMPTY_JSON_STRING;
	}
	
	/**
	 * 更新指定用户的用户信息
	 * @param user 用户的更新信息
	 * @return 更新后用户的dataTables格式的JSON字符串
	 */
	public String updateUser(User user){
		userMapper.updateUser(user);
		
		return JsonConverter.dataTablesJsonFormat(user);
	}
	
	/**
	 * 查找所有的用户信息
	 * @return 以dataTables格式返回所有的用户信息
	 */
	public String findAllUsers(){
		String jsonString = JsonConverter.EMPTY_JSON_STRING;
		List<User> userList = userMapper.findAllUsers();
		
		if(userList != null){
			jsonString = JsonConverter.objectToJson(userList);
		}
		
		return jsonString;
	}
}
