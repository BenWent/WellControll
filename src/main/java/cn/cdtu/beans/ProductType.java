package cn.cdtu.beans;

import java.io.Serializable;

/**
 * 产品类型
 * @author Happy
 *
 * @since 2018年4月28日
 */
public class ProductType implements Serializable{
	private static final long serialVersionUID = 0X2dd34b7dL;
	
	private String id;
	private String name;
	private String specification;
	
	public ProductType(){
		
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

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Override
	public String toString() {
		return "ProductType [id=" + id + ", name=" + name + ", specification=" + specification + "]";
	}
}
