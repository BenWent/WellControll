package cn.cdtu.mappers;

import cn.cdtu.beans.StoreHouse;

/**
 *  
 * @author Happy
 *
 * @since 2018年4月29日
 */
public interface StoreHouseMapper {
	
	/**
	 * 新建一个仓库
	 * @param storeHouse 新建的仓库信息
	 */
	public abstract void insertStoreHouse(StoreHouse storeHouse);
	
	/**
	 * 
	 * @param id 仓库的ID
	 * @return 查询到的仓库信息
	 */
	public abstract StoreHouse findStoreHouseById(String id);

	/**
	 * 
	 * @param name 仓库的名字
	 * @return 查询到的仓库的信息
	 */
	public abstract StoreHouse findStoreHouseByName(String name);

	/**
	 * 
	 * @param id 待移除仓库的id
	 */
	public abstract void removeStoreHouseById(String id);
	
	/**
	 * 
	 * @param name 待移除仓库的名字
	 */
	public abstract void removeStoreHouseByName(String name);

	/**
	 * 清除仓库
	 */
	public abstract void removeAllStoreHouses();
}
