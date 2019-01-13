package cn.cdtu.beans;

import java.io.Serializable;

/**
 * 操作
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public class Operation implements Serializable{
	private static final long serialVersionUID = 0X4df28143L;
	
	private String id;
	private String name;
	private String address;
	private String mark;

	public Operation() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", name=" + name + ", address=" + address + ", mark=" + mark + "]";
	}
}
