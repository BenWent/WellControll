package cn.cdtu.beans;

import java.io.Serializable;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public class Standard implements Serializable{
	private static final long serialVersionUID = 0Xfc501e71L;
	
	private String id;
	private String specification;
	private String name;
	private String sampleType;
	private String domainCode;
	private String limitedField;
	private String mark;
	private String address;
	private char status;

	public Standard() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public String getDomainCode() {
		return domainCode;
	}

	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	
	public String getLimitedField(){
		return this.limitedField;
	}
	
	public void setLimitedField(String limitedField){
		this.limitedField = limitedField;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Standard [id=" + id + ", specification=" + specification + ", name=" + name + ", sampleType=" + sampleType
				+ ", domainCode=" + domainCode + ", mark=" + mark + ", address=" + address + ", status=" + status + "]";
	}

}
