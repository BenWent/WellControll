package cn.cdtu.beans;

import java.io.Serializable;

/**
 * 井控产品
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 0X3d4beceaL;

	private String id;
	private String name;

	// 井控产品制造厂商
	private Manufacturer manufacturer;
	// 井控产品类型
	private ProductType productType;
	// 井控产品的抽查地点
	private StoreHouse storeHouse;
	// 操作用户
	private User user;

	// 井控产品抽查条目
	private String items;
	private char status;

	public Product() {
		status = '0';
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

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public StoreHouse getStoreHouse() {
		return storeHouse;
	}

	public void setStoreHouse(StoreHouse storeHouse) {
		this.storeHouse = storeHouse;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", manufacturer=" + manufacturer + ", productType="
				+ productType + ", storeHouse=" + storeHouse + ", items=" + items + ", status=" + status + ", user="
				+ user + "]";
	}
}
