package cn.cdtu.beans;

import java.io.Serializable;

/**
 * 仓库
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public class StoreHouse implements Serializable{
	private static final long serialVersionUID = 0Xe20a0f11L;
	
	private String id;
	private String name;

	public StoreHouse() {

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

	@Override
	public String toString() {
		return "StoreHouse [id=" + id + ", name=" + name + "]";
	}
}
