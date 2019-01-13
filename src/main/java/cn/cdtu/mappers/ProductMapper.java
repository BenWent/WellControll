package cn.cdtu.mappers;

import java.util.List;

import cn.cdtu.beans.Product;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
public interface ProductMapper {
	/**
	 * 新建一种产品
	 * @param product 新建的产品信息
	 */
	public abstract void insertProduct(Product product);
	
	/**
	 * 删除所有的产品信息
	 */
	public abstract void removeAllProducts();
	
	/**
	 * 删除指定的产品信息
	 * @param id 指定产品信息的唯一标识符
	 */
	public abstract void removeProductById(String id);
	
	/**
	 * 更新产品信息
	 * @param product 更新后的产品信息
	 */
	public abstract void updateProduct(Product product);
	
	/**
	 * 查询所有的产品信息
	 * @return 查询到的所有产品信息
	 */
	public abstract List<Product> findAllProducts();
	
	/**
	 * 
	 * @param id 指定的产品信息的唯一标识符
	 * @return 查询到的产品信息
	 */
	public abstract Product findProductById(String id);
	
	/**
	 * 
	 * @param name 指定的产品信息的name
	 * @return 查询到的产品信息
	 */
	public abstract Product findProductByName(String name);

	/**
	 * 统计name指定的厂商的数量
	 * @param name 厂商的名字
	 * @return 厂商的数量
	 */
	public abstract int countManufacturerByName(String name);
	
	/**
	 * 统计name指定的产品类型的数量
	 * @param name 产品类型的名字
	 * @return 产品类型的数量
	 */
	public abstract int countProductTypeByName(String name);
	
	/**
	 * 统计name指定的仓库的数量
	 * @param name 仓库的名字
	 * @return 仓库的数量
	 */
	public abstract int countStoreHouseByName(String name);
}
