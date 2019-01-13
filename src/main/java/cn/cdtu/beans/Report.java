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
public class Report implements Serializable {
	private static final long serialVersionUID = 0X1d46d56b;

	private String id;

	// 执行的取样计划
	private SamplePlan samplePlan;
	// 操作用户
	private User user;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date checkDate;
	// 检测失败的项
	private String failedItems;
	private char result;
	private String address;
	private String imagePath;
	private String mark;
	
	private char status;
	//用户在ReportMapper.xml中提前获取当前数据库中的提交状态
	private char db_status;
	
	private char recheck;
	private char store;
	// 是否委外
	private char isOutsoucing;
	private char isPost;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date postTime;
	private String postReceipt;
	private String postAddress;
	private String contact;
	private String contactPhoneNumber;

	public Report() {
		result = '0';
		status = '0';
		recheck = '0';
		store = '0';
		isOutsoucing = '0';
		isPost = '0';
		
		db_status = '0';
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SamplePlan getSamplePlan() {
		return samplePlan;
	}

	public void setSamplePlan(SamplePlan samplePlan) {
		this.samplePlan = samplePlan;
	}

	public String getFailedItems() {
		return failedItems;
	}

	public void setFailedItems(String failedItems) {
		this.failedItems = failedItems;
	}

	public char getResult() {
		return result;
	}

	public void setResult(char result) {
		this.result = result;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	public char getDb_status() {
		return db_status;
	}

	public void setDb_status(char db_status) {
		this.db_status = db_status;
	}

	public char getRecheck() {
		return recheck;
	}

	public void setRecheck(char recheck) {
		this.recheck = recheck;
	}

	public char getStore() {
		return store;
	}

	public void setStore(char store) {
		this.store = store;
	}

	public char getIsOutsoucing() {
		return isOutsoucing;
	}

	public void setIsOutsoucing(char isOutsoucing) {
		this.isOutsoucing = isOutsoucing;
	}

	public char getIsPost() {
		return isPost;
	}

	public void setIsPost(char isPost) {
		this.isPost = isPost;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public String getPostReceipt() {
		return postReceipt;
	}

	public void setPostReceipt(String postReceipt) {
		this.postReceipt = postReceipt;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", samplePlan=" + samplePlan + ", user=" + user + ", checkDate=" + checkDate
				+ ", failedItems=" + failedItems + ", result=" + result + ", address=" + address + ", imagePath="
				+ imagePath + ", mark=" + mark + ", status=" + status + ", recheck=" + recheck + ", store=" + store
				+ ", isOutsoucing=" + isOutsoucing + ", isPost=" + isPost + ", postTime=" + postTime + ", postAddress="
				+ postAddress + ", contact=" + contact + ", contactPhoneNumber=" + contactPhoneNumber + "]";
	}
}
