package cn.cdtu.mappers;


import java.util.List;

import cn.cdtu.beans.User;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public interface UserMapper {
	/**
	 * 新建一个用户
	 * @param user 新建用户的信息 
	 */
	public abstract void insertUser(User user);
	
	/**
	 * 删除一个用户
	 * @param name 待删除用户的用户名 
	 */
	public abstract void removeUserByName(String name);
	
	/**
	 * 更新用户信息(包括用户密码和用户的权限)
	 * @param user 待更新信息的用户
	 */
	public abstract void updateUser(User user);
	
	/**
	 * 查询所有的用户信息
	 * @return 查询到的用户集合
	 */
	public abstract List<User> findAllUsers();
	
	/**
	 * 根据用户名返回查询到的用户
	 * @param name 指定的用户名
	 * @return user 查询到的用户信息
	 */
	public User findUserByName(String name);
}
