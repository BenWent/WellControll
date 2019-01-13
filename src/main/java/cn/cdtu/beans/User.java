package cn.cdtu.beans;

import java.io.Serializable;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public class User implements Serializable{
	private static final long serialVersionUID = 0Xe986324dL;
	
	private String name;
	private String password;
	private char privilege;

	public User() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public char getPrivilege() {
		return privilege;
	}

	public void setPrivilege(char privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", privilege=" + privilege + "]";
	}
}
