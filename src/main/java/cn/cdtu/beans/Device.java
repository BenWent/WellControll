package cn.cdtu.beans;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public class Device implements Serializable{
	private static final long serialVersionUID = 0X6e4ca0b8L;
	
	private String id;
	private String name;
	private String type;

	// 设备制造商
	private Manufacturer manufacturer;

	private int amount;
	private String number;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginningStartUseTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date overdueDate;
	private float cost;
	private String address;
	private String mark;

	public Device() {

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getBeginningStartUseTime() {
		return beginningStartUseTime;
	}

	public void setBeginningStartUseTime(Date beginningStartUseTime) {
		this.beginningStartUseTime = beginningStartUseTime;
	}

	public Date getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
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
		return "Device [id=" + id + ", name=" + name + ", type=" + type + ", manufacturer=" + manufacturer + ", amount="
				+ amount + ", number=" + number + ", beginningStartUseTime=" + beginningStartUseTime + ", overdueDate="
				+ overdueDate + ", cost=" + cost + ", address=" + address + ", mark=" + mark + "]";
	}
}
