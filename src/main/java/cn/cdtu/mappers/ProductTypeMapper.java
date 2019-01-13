package cn.cdtu.mappers;

import cn.cdtu.beans.ProductType;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
public interface ProductTypeMapper {
	
	/**
	 * 新建产品类型
	 * @param productType 指定的产品类型
	 */
	public abstract void insertProductType(ProductType productType);

	/**
	 * 
	 * @param id 待查询产品类型的ID
	 * @return 查询到的产品类型
	 */
	public abstract ProductType findProductTypeById(String id);
	
	/**
	 * 
	 * @param name 带查询产品的名字
	 * @return 查询到的产品信息
	 */
	public abstract ProductType findProductTypeByName(String name);

	/**
	 * 
	 * @param id 待移除产品类型的id
	 */
	public abstract void removeProductTypeById(String id);
	
	/**
	 * 
	 * @param name 待移除产品类型的名字
	 */
	public abstract void removeProductTypeByName(String name);

	/**
	 * 清除产品类型
	 */
	public abstract void removeAllProductTypes();
}
