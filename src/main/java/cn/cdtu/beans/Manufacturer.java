package cn.cdtu.beans;

import java.io.Serializable;

/**
 * 厂商
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public class Manufacturer implements Serializable{
	private static final long serialVersionUID = 0X5edc04c0L;
	
	private String id;
	private String name;

	public Manufacturer() {

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
		return "Manufactor [id=" + id + ", name=" + name + "]";
	}
}
