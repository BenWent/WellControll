package cn.cdtu.mappers;

import java.util.List;

import cn.cdtu.beans.Device;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public interface DeviceMapper {
	/**
	 * 插入设备信息
	 * @param device 指定的设备信息
	 */
	public abstract void insertDevice(Device device);
	
	/**
	 * 删除所有的设备信息
	 */
	public abstract void removeAllDevices();
	
	/**
	 * 根据指定的ID删除设备信息
	 * @param id
	 */
	public abstract void removeDeviceById(String id);
	
	/**
	 * 更新设备信息
	 * @param device 更新后的设备信息
	 */
	public abstract void updateDevice(Device device);
	
	/**
	 * 查询所有的设备信息
	 * @return 查询到的设备信息集合
	 */
	public abstract List<Device> findAllDevices();
	
	/**
	 * 根据指定ID查询设备信息
	 * @param id 指定的设备ID
	 * @return 查询到的设备信息
	 */
	public abstract Device findDeviceById(String id);

	/**
	 * 统计name指定的厂商的数量
	 * @param name 厂商的名字
	 * @return 厂商的数量
	 */
	public abstract int countManufacturerByName(String name);
}