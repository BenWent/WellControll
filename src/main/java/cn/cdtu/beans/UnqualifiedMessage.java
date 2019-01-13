package cn.cdtu.beans;

/**
 * 
 * @author happy
 *
 * @since 2018年5月4日
 */
public class UnqualifiedMessage {
	//产品的厂商名
	private String companyName;
	//该厂商不合格合格产品的数量
	private int unqualifiedProductCount;

	public UnqualifiedMessage() {

	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getQualifiedProduct() {
		return unqualifiedProductCount;
	}

	public void setQualifiedProduct(int unqualifiedProductCount) {
		this.unqualifiedProductCount = unqualifiedProductCount;
	}

	@Override
	public String toString() {
		return "QualifiedMessage [companyName=" + companyName + ", unqualifiedProductCount=" + unqualifiedProductCount + "]";
	}
}
