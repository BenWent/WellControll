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
public class SamplePlan implements Serializable{
	private static final long serialVersionUID = 0X0a837896L;

	private String id;
	
	//抽查的产品
	private Product product;
	//抽查执行的标准
	private Standard standard;
	//对该抽查负责的用户
	private User user;
	
	//抽查的批次
	private int batch;
	private String mark;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	public SamplePlan(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SamplePlan [id=" + id + ", product=" + product + ", standard=" + standard + ", user="
				+ user + ", batch=" + batch + ", mark=" + mark + ", date=" + date + "]";
	}
}
