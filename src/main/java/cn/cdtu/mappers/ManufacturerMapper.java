package cn.cdtu.mappers;

import cn.cdtu.beans.Manufacturer;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
public interface ManufacturerMapper {
	
	/**
	 * 
	 * @param id 指定制造商的id
	 * @return 查询到的制造商
	 */
	public abstract Manufacturer findManufacturerById(String id);
	
	/**
	 * 
	 * @param name 指定制造商的name
	 * @return 查询到的制造商
	 */
	public abstract Manufacturer findManufacturerByName(String name);

	/**
	 * 
	 * @param manufacturer 新增的制造商
	 */
	public abstract void insertManufacturer(Manufacturer manufacturer);

	/**
	 * 
	 * @param name 待删除厂商的id
	 */
	public abstract void removeManufacturerById(String id);
	
	/**
	 * 
	 * @param name 待删除厂商的名字
	 */
	public abstract void removeManufacturerByName(String name);
}
